package com.sunilos.ctl;

import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.bcel.classfile.EnumElementValue;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.MenuItem;
import com.sunilos.common.ORSResponse;
import com.sunilos.common.UserContext;
import com.sunilos.dto.UserDTO;
import com.sunilos.form.ForgetPasswordForm;
import com.sunilos.form.LoginForm;
import com.sunilos.form.UserForm;
import com.sunilos.form.UserRegistrationForm;
import com.sunilos.service.UserServiceInt;
import com.sunilos.config.JwtTokenUtil;

/**
 * Login controller provides API for Sign Up, Sign In and Forgot password
 * operations
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	/**
	 * Finds user by login id
	 * 
	 * @param loginId
	 * @return
	 */
	
	
	@Autowired(required = true)
	private JwtTokenUtil jwtTokenUtil;

	
	@Autowired
	private UserDetailsService jwtService; 
	
	@GetMapping("login/{loginId}")
	public ORSResponse get(@PathVariable String loginId) {
		
		System.out.println("this is login ID--------------------------"+loginId);
		ORSResponse res = new ORSResponse(true);
		UserDTO dto = baseService.findByLoginId(loginId, userContext);
		System.out.println("User " + dto);
		if (dto != null) {
			System.out.println("userDTO is not null in LoginCTL");
			UserDTO userDTO = new UserDTO();
			userDTO.setFirstName(dto.getFirstName());
			userDTO.setLastName(dto.getLastName());
			userDTO.setLoginId(dto.getLoginId());
			res.addData(userDTO);
		} else {
			System.out.println("userDTO is null in LoginCTL");
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {
      System.out.println("login Ctl login called");
		ORSResponse res = valiate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}
        
		UserDTO dto = baseService.authenticate(form.getLoginId(), form.getPassword());
		 System.out.println("login Ctl > baseService authentic called");
		if (dto == null) {
			System.out.println("login Ctl > dto==null");
			
			res.setSuccess(false);
			res.addMessage("Invalid ID or Password");
		} else {
			System.out.println("login Ctl > dto not null");
			UserContext context = new UserContext(dto);
			System.out.println("this iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii-"+context.getLoginId());
			session.setAttribute("userContext", context);
			res.setSuccess(true);
			res.addData(dto);
		res.addResult("jsessionid", session.getId());
			
			final UserDetails userDetails = jwtService.loadUserByUsername(form.getLoginId());
			System.out.println("After Service runnning");
			final String token = jwtTokenUtil.generateToken(userDetails);
			System.out.println("After Token");
			res.addResult("token", token);
			
			System.out.println("jsessionid " + session.getId());
		}
		
		System.out.println("Login Uer context : " + session.getAttribute("userContext")) ;
		return res;

	}
	/*
	 * @PostMapping("logout") public ORSResponse logout(HttpSession session) {
	 * 
	 * ORSResponse res = new ORSResponse(true); session.invalidate();
	 * res.addMessage("Logout Succesfully"); return res;
	 * 
	 * 
	 * }
	 */

	/**
	 * Emails password to user
	 * 
	 * @return
	 */
	@PostMapping("fp")
	public ORSResponse forgotPassword(@RequestBody @Valid ForgetPasswordForm form,BindingResult bindingResult, HttpServletRequest request) {
		System.out.println("1-- forgot password method called ");
		
		ORSResponse res= valiate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		
		System.out.println("this is login ID--------------------------"+form.getLogin());
		
		Enumeration<String> e =  request.getHeaderNames();
		
		String key = null;
		while ( e.hasMoreElements() ){
			System.out.println("2-- inside while loop ");
			key = e.nextElement();
			System.out.println(key + " = " + request.getHeader(key));
		}
		
		
		UserDTO dto = this.baseService.forgotPassword(form.getLogin());
		System.out.println("3-- dto got "+dto.getLoginId());
		if (dto == null) {
			System.out.println("4-- dto is null ");
			res.setSuccess(false);
			res.addMessage("Invalid Login Id");
		} else {
			System.out.println("5-- dto is not null response is true ");
			res.setSuccess(true);
			res.addMessage("Password has been sent to email id");
		}
		return res;
	}

	/**
	 * Register new user
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = valiate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseService.findByLoginId(form.getLogin(), userContext);

		if (dto != null) {
			res.setSuccess(false);
			res.addMessage("Login Id already exists");
			return res;
		}
		
		dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLogin());
		dto.setGender(form.getGender());
		dto.setDob(form.getDob());
		dto.setPhone(form.getMobileNo());
		dto.setPassword(form.getPassword());
		
		baseService.register(dto);

		res.setSuccess(true);
		res.addMessage("User has been registered");
		return res;
	}

	@GetMapping("menu")
	public ORSResponse menu(HttpSession session) {

		LinkedHashSet<MenuItem> menuBar = new LinkedHashSet<MenuItem>();

		MenuItem std = new MenuItem("Student", "/student");
		std.addSubmenu("New Student", "/student");
		std.addSubmenu("Student List", "/studentlist");

		menuBar.add(std);

		MenuItem coll = new MenuItem("College", "/college");
		coll.addSubmenu("New College", "/college");
		coll.addSubmenu("College List", "/collegelist");

		menuBar.add(coll);
		
		

		ORSResponse res = new ORSResponse(true);
		res.addData(menuBar);
		res.setSuccess(true);
		return res;
	}

}

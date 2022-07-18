package com.sunilos.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.sunilos.common.BaseDAOImpl;
import com.sunilos.dao.UserDAOInt;
import com.sunilos.dto.UserDTO;


@Service
public class JwtUserDetailsService extends BaseDAOImpl<UserDTO> implements UserDetailsService {

	@Autowired
	private UserDAOInt userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username +" --username is in service ");
		UserDTO user = userDao.findByUniqueKey("loginId",username, null);
		System.out.println("After findByEmail run");
		if (user == null) {
			System.out.println("user found nuulllll");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		System.out.println("At return statement");
		return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
				new ArrayList<>());
	

	}


	@Override
	protected List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder builder, Root<UserDTO> qRoot) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Class<UserDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
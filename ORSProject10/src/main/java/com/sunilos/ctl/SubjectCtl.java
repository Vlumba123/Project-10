package com.sunilos.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.dto.CourseDTO;
import com.sunilos.dto.StudentDTO;
import com.sunilos.dto.SubjectDTO;
import com.sunilos.form.SubjectForm;
import com.sunilos.service.CourseServiceInt;
import com.sunilos.service.SubjectServiceInt;

@RestController
@RequestMapping(value= "Subject")
public class SubjectCtl extends BaseCtl<SubjectForm, SubjectDTO, SubjectServiceInt> {
	
	@Autowired
	private CourseServiceInt courseservice;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		System.out.println("inside subjectctl preload method-------------------------------");
		List<CourseDTO> list = courseservice.search(new CourseDTO(), userContext);
		res.addResult("courseList", list);
		System.out.println("this is response"+ res);
		return res;
	}
}


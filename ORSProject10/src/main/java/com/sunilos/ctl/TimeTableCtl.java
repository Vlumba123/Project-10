package com.sunilos.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.dto.CourseDTO;
import com.sunilos.dto.SubjectDTO;
import com.sunilos.dto.TimetableDTO;
import com.sunilos.form.TimetableForm;
import com.sunilos.service.CourseServiceInt;
import com.sunilos.service.SubjectServiceInt;
import com.sunilos.service.TimetableServiceInt;

@RestController
@RequestMapping(value="Timetable")
public class TimeTableCtl extends BaseCtl<TimetableForm, TimetableDTO, TimetableServiceInt> {
	
	
	@Autowired
	private CourseServiceInt courseserviceint;
	@Autowired
	private SubjectServiceInt subjectserviceint;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		System.out.println("inside timetablectl preload method-------------------------------");
		
		 List<CourseDTO> clist = courseserviceint.search(new CourseDTO(),userContext);
		 
		 
		
		  List<SubjectDTO> slist= subjectserviceint.search(new SubjectDTO(), userContext);
		 
		 res.addResult("courseList", clist); 
		res.addResult("subjectList", slist); 
		System.out.println("this is response"+ res);
		return res;
	}
}

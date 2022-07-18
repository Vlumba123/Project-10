package com.sunilos.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.CourseDTO;
import com.sunilos.dto.FacultyDTO;
import com.sunilos.dto.SubjectDTO;
import com.sunilos.form.FacultyForm;
import com.sunilos.service.CollegeServiceInt;
import com.sunilos.service.FacultyServiceInt;
import com.sunilos.service.SubjectServiceInt;

@RestController
@RequestMapping(value="Faculty")
public class FacultyCtl extends BaseCtl<FacultyForm, FacultyDTO, FacultyServiceInt> {

	
	@Autowired
	CollegeServiceInt collegeserviceint;
	
	@Autowired
	SubjectServiceInt subjectserviceint;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		System.out.println("inside timetablectl preload method-------------------------------");
		
		 List<CourseDTO> clist = collegeserviceint.search(new CollegeDTO(),userContext);
		 
		 
		
		  List<SubjectDTO> slist= subjectserviceint.search(new SubjectDTO(), userContext);
		 
		 res.addResult("collegeList", clist); 
		res.addResult("subjectList", slist); 
		System.out.println("this is response"+ res);
		return res;
	}
}

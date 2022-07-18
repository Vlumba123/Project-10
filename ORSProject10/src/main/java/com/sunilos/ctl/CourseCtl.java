package com.sunilos.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.dto.CourseDTO;
import com.sunilos.form.CourseForm;
import com.sunilos.service.CourseServiceInt;

@RestController
@RequestMapping(value= "course")
public class CourseCtl extends BaseCtl<CourseForm, CourseDTO, CourseServiceInt> {
	


    
}

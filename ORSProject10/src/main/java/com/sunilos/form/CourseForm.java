package com.sunilos.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.dto.CourseDTO;

public class CourseForm extends BaseForm {

	
	@NotEmpty
	private String courseName ;
	@NotEmpty
	private String discription ;
	@NotEmpty
	private String duration;

	public String getCoursename() {
		return courseName;
	}

	public void setCoursename(String courseName) {
		this.courseName = courseName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public BaseDTO getDto() {
		// TODO Auto-generated method stub
		System.out.println("inside course from--------------");
		CourseDTO dto =  initDTO(new CourseDTO());
		dto.setCoursename(courseName);
		dto.setDiscription(discription);
		dto.setDuration(duration);
		return dto;
	}
	
	
	
}

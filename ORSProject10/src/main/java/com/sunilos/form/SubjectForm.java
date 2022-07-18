package com.sunilos.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.dto.SubjectDTO;

public class SubjectForm extends BaseForm {
	
//	
    private String courseName;
	
	
    @NotNull
	private long courseId;
    
    @NotEmpty
	private String subjectName;


    @NotEmpty
	private String description;


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public long getCourseId() {
		return courseId;
	}


	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public BaseDTO getDto() {
        
		SubjectDTO dto=	initDTO(new SubjectDTO());
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setSubjectName(subjectName);
		dto.setDescription(description);
		System.out.println("inside subject form.......................................");
		System.out.println(dto.getCourseId()+"'''"+dto.getCourseName());
		return dto;
	}

	
	

}

package com.sunilos.form;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.dto.TimetableDTO;

public class TimetableForm extends BaseForm {

	
	
	private String courseName;

	@NotNull
	private Long courseId;

	
	private String subjectName;

	@NotNull
	private Long subjectId;

	@NotNull
	private Date examDate;

	@NotEmpty 
	private String examTime;

	@NotEmpty
	private String semester;


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public Long getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}


	public Date getExamDate() {
		return examDate;
	}


	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}


	public String getExamTime() {
		return examTime;
	}


	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}


	@Override
	public BaseDTO getDto() {
		TimetableDTO dto = initDTO(new TimetableDTO());
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setSubjectId(subjectId);
		dto.setSubjectName(subjectName);
		dto.setSemester(semester);
		dto.setExamDate(examDate);
		dto.setExamTime(examTime);
		return dto;
	}

	
	



}

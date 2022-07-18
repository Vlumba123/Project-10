package com.sunilos.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.sunilos.common.BaseDTO;


@Entity
@Table (name = "ST_SUBJECT")
public class SubjectDTO extends BaseDTO {
	
	
	@Column(name= "Course_Name" , length = 50)
	private String courseName;
	
	/**
	 *  ID of Course
	 */
	@Column(name = "Course_Id") 
	private long courseId;
	
	/**
	 *  Name of Subject
	 */
	@Column(name = "Subject_Name", length = 50)
	private String subjectName;

	/**
	 * Description
	 */
	@Column(name = "Description", length = 50)
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
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String , String> map = new LinkedHashMap<String, String>();
		map.put("subjectName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

package com.sunilos.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;

@Entity
@Table(name = "ST_COURSE")
public class CourseDTO extends BaseDTO {

	
	@Column(name="COURSENAME" , length=50)
	private String courseName ;
	@Column(name="DISCRIPTION" , length=50)
	private String discription ;
	@Column(name="DURATION" , length=50)
	private String duration;
	
	

	public String getCoursename() {
		return courseName;
	}

	public void setCoursename(String coursename) {
		this.courseName = coursename;
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
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("courseName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}

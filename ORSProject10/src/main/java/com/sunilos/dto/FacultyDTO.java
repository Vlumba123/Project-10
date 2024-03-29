package com.sunilos.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;

@Entity
@Table(name = "RT_Faculty")
public class FacultyDTO extends BaseDTO {

	/**
	 * firstName of Faculty
	 */
	@Column(name = "First_Name", length = 50)
	private String firstName;

	/**
	 * lastName of Faculty
	 */
	@Column(name = "Last_Name", length = 50)
	private String lastName;

	/**
	 * emailId of Faculty
	 */
	@Column(name = "Login_Id", length = 50)
	private String loginId;

	/**
	 * Mobile Number of Faculty
	 */
	@Column(name = "Mobile_No", length = 50)
	private String mobileNo;

	/**
	 * Date of Birth of Faculty
	 */
	@Column(name = "Date_Of_Joining")
	private Date doj;

	/**
	 * collegeName of Faculty
	 */
	@Column(name = "College_Name", length = 50)
	private String collegeName;

	/**
	 * collegeId of Faculty
	 */
	@Column(name = "College_Id")
	private Long collegeId;

	/**
	 * subjectName of Faculty
	 */
	@Column(name = "Subject_Name", length = 50)
	private String subjectName;

	/**
	 * subjectId of Faculty
	 */
	@Column(name = "Subject_Id")
	private Long subjectId;

	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
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

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}

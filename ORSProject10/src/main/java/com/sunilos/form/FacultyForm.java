package com.sunilos.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.dto.FacultyDTO;

public class FacultyForm extends BaseForm {

	
    @NotEmpty
	private String firstName;

    @NotEmpty
	private String lastName;

    @NotEmpty
    @Email
	private String loginId;

    @NotEmpty
    
	private String mobileNo;

    @NotNull
	private Date doj;

    
	private String collegeName;

	@NotNull
	private Long collegeId;


	private String subjectName;

	@NotNull
	private Long subjectId;


	@Override
	public BaseDTO getDto() {
		FacultyDTO dto= initDTO(new FacultyDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setCollegeId(collegeId);
		dto.setSubjectId(subjectId);
		dto.setMobileNo(mobileNo);
		dto.setLoginId(loginId);
		return dto;
	}

}

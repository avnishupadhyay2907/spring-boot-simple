package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.StudentDTO;
import com.rays.dto.SubjectDTO;

public class StudentForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@NotEmpty(message = "Email ID is required")
	private String emailId;

	@NotEmpty(message = "Mobile Number is required")
	private String mobileNo;

	@NotNull(message = "Date of Birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private long collegeId;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	// Convert form to DTO
	@Override
	public BaseDTO getDto() {
		StudentDTO dto = (StudentDTO) initDTO(new StudentDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmailId(emailId);
		dto.setMobileNo(mobileNo);
		dto.setCollegeId(collegeId);
		dto.setDob(dob);
		return dto;
	}

}

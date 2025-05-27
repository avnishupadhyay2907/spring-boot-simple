package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.FacultyDTO;
import com.rays.dto.SubjectDTO;

public class FacultyForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@NotEmpty(message = "Gender is required")
	private String gender;

	@NotEmpty(message = "Qualification is required")
	private String qualification;

	@NotEmpty(message = "Mobile Number is required")
	private String mobileNo;

	@NotEmpty(message = "Email ID is required")
	private String emailId;

	@NotNull(message = "Date of Birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private long collegeId;

	private long courseId;

	private long subjectId;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	// Convert form to DTO
	@Override
	public BaseDTO getDto() {
		FacultyDTO dto = (FacultyDTO) initDTO(new FacultyDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setGender(gender);
		dto.setQualification(qualification);
		dto.setMobileNo(mobileNo);
		dto.setEmailId(emailId);
		dto.setDob(dob);
		dto.setCollegeId(collegeId);
		dto.setCourseId(courseId);
		dto.setSubjectId(subjectId);

		return dto;
	}

}

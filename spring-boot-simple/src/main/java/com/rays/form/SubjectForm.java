package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.SubjectDTO;

public class SubjectForm extends BaseForm {

	@NotNull(message = "Course ID is required")
	private Long courseId;

	@NotEmpty(message = "Subject Name is required")
	private String subjectName;

	@NotEmpty(message = "Course Name is required")
	private String courseName;

	@NotEmpty(message = "Description is required")
	private String description;

	// Getters and Setters

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Convert form to DTO
	@Override
	public BaseDTO getDto() {
		SubjectDTO dto = (SubjectDTO) initDTO(new SubjectDTO());
		dto.setCourseId(courseId);
		dto.setSubjectName(subjectName);
		dto.setCourseName(courseName);
		dto.setDescription(description);
		return dto;
	}
}

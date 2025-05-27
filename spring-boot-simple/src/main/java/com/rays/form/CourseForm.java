package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.CourseDTO;

public class CourseForm extends BaseForm {

	@NotEmpty(message = "Course Name is required")
	private String courseName;

	@NotEmpty(message = "Description is required")
	private String description;

	@NotEmpty(message = "Duration is required")
	private String duration;

	// Getters and Setters

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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	// Convert form to DTO
	@Override
	public BaseDTO getDto() {
		CourseDTO dto = (CourseDTO) initDTO(new CourseDTO());
		dto.setCourseName(courseName);
		dto.setDescription(description);
		dto.setDuration(duration);
		return dto;
	}
}

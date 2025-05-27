package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_COURSE")
public class CourseDTO extends BaseDTO {

	@Column(name = "COURSE_NAME", length = 50)
	private String courseName;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	@Column(name = "DURATION", length = 50)
	private String duration;

	// Getters and setters
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

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return courseName;
	}
}

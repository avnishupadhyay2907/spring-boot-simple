package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_SUBJECT")
public class SubjectDTO extends BaseDTO {

	@Column(name = "COURSE_ID")
	private long courseId;

	@Column(name = "SUBJECT_NAME", length = 100)
	private String subjectName;

	@Column(name = "COURSE_NAME", length = 100)
	private String courseName;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	// Getters and setters
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

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}
}

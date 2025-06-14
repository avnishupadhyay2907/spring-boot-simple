package com.rays.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseDTO implements DropDownList {

	@Id
	@GeneratedValue(generator = "avnishPk")
	@GenericGenerator(name = "avnishPk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

}

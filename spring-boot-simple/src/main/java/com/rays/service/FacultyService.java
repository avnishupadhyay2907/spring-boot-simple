package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.FacultyDAO;
import com.rays.dto.FacultyDTO;

@Service
@Transactional
public class FacultyService {

	@Autowired
	public FacultyDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(FacultyDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(FacultyDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			FacultyDTO dto = findById(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public FacultyDTO findById(long pk) {
		FacultyDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(FacultyDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(FacultyDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

}

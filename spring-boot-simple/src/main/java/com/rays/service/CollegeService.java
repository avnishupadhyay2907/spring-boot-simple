package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.CollegeDAO;
import com.rays.dao.UserDAO;
import com.rays.dto.CollegeDTO;

@Service
@Transactional
public class CollegeService {

	@Autowired
	public CollegeDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CollegeDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CollegeDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			CollegeDTO dto = findById(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public CollegeDTO findById(long pk) {
		CollegeDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(CollegeDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public CollegeDTO findByLogin(String login) {
		CollegeDTO dto = dao.findByUniqueKey("loginId", login);
		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(CollegeDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

}

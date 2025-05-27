package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.CollegeDTO;
import com.rays.dto.CourseDTO;
import com.rays.dto.FacultyDTO;
import com.rays.dto.SubjectDTO;

@Repository
public class FacultyDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public CourseDAO courseDao;

	@Autowired
	public CollegeDAO collegeDao;

	@Autowired
	public SubjectDAO subjectDao;

	public void populate(FacultyDTO dto) {
		CourseDTO courseDto = courseDao.findByPk(dto.getCourseId());

		CollegeDTO collegeDto = collegeDao.findByPk(dto.getCollegeId());

		SubjectDTO subjectDTO = subjectDao.findByPk(dto.getSubjectId());

		dto.setCourseName(courseDto.getCourseName());

		dto.setCollegeName(collegeDto.getName());

		dto.setSubjectName(subjectDTO.getSubjectName());
	}

	public long add(FacultyDTO dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(FacultyDTO dto) {
		populate(dto);
		entityManager.merge(dto);
	}

	public void delete(FacultyDTO dto) {
		entityManager.remove(dto);
	}

	public FacultyDTO findByPk(long pk) {
		FacultyDTO dto = entityManager.find(FacultyDTO.class, pk);
		return dto;
	}

	public FacultyDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<FacultyDTO> cq = builder.createQuery(FacultyDTO.class);

		Root<FacultyDTO> qRoot = cq.from(FacultyDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<FacultyDTO> tq = entityManager.createQuery(cq);

		List<FacultyDTO> list = tq.getResultList();

		FacultyDTO dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}
		return dto;
	}

	public List search(FacultyDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<FacultyDTO> cq = builder.createQuery(FacultyDTO.class);

		Root<FacultyDTO> qRoot = cq.from(FacultyDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {

			if (dto.getCourseId() != 0 && dto.getCourseId() > 0) {
				predicateList.add(builder.like(qRoot.get("courseId"), dto.getCourseId() + "%"));
			}
			if (dto.getCollegeId() != 0 && dto.getCollegeId() > 0) {
				predicateList.add(builder.like(qRoot.get("collegeId"), dto.getCollegeId() + "%"));
			}
			if (dto.getSubjectId() != 0 && dto.getSubjectId() > 0) {
				predicateList.add(builder.like(qRoot.get("subjectId"), dto.getSubjectId() + "%"));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				predicateList.add(builder.like(qRoot.get("gender"), dto.getGender() + "%"));
			}
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<FacultyDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<FacultyDTO> list = tq.getResultList();

		return list;
	}

}

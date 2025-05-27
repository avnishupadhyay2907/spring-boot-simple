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

import com.rays.dto.CourseDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.SubjectDTO;
import com.rays.dto.UserDTO;

@Repository
public class SubjectDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public CourseDAO courseDao;

	public void populate(SubjectDTO dto) {
		CourseDTO courseDto = courseDao.findByPk(dto.getCourseId());
		dto.setCourseName(courseDto.getCourseName());
	}

	public long add(SubjectDTO dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(SubjectDTO dto) {
		populate(dto);
		entityManager.merge(dto);
	}

	public void delete(SubjectDTO dto) {
		entityManager.remove(dto);
	}

	public SubjectDTO findByPk(long pk) {
		SubjectDTO dto = entityManager.find(SubjectDTO.class, pk);
		return dto;
	}

	public SubjectDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<SubjectDTO> cq = builder.createQuery(SubjectDTO.class);

		Root<SubjectDTO> qRoot = cq.from(SubjectDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<SubjectDTO> tq = entityManager.createQuery(cq);

		List<SubjectDTO> list = tq.getResultList();

		SubjectDTO dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}
		return dto;
	}

	public List search(SubjectDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<SubjectDTO> cq = builder.createQuery(SubjectDTO.class);

		Root<SubjectDTO> qRoot = cq.from(SubjectDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {

			if (dto.getCourseId() != 0 && dto.getCourseId() > 0) {
				predicateList.add(builder.like(qRoot.get("courseId"), dto.getCourseId() + "%"));
			}
			if (dto.getSubjectName() != null && dto.getSubjectName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				predicateList.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
			}
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<SubjectDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<SubjectDTO> list = tq.getResultList();

		return list;
	}

}

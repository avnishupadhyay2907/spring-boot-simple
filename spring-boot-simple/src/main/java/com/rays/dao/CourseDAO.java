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

import org.springframework.stereotype.Repository;

import com.rays.dto.CourseDTO;

@Repository
public class CourseDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(CourseDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(CourseDTO dto) {

		entityManager.merge(dto);
	}

	public void delete(CourseDTO dto) {
		entityManager.remove(dto);
	}

	public CourseDTO findByPk(long pk) {
		CourseDTO dto = entityManager.find(CourseDTO.class, pk);
		return dto;
	}

	public CourseDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<CourseDTO> cq = builder.createQuery(CourseDTO.class);

		Root<CourseDTO> qRoot = cq.from(CourseDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<CourseDTO> tq = entityManager.createQuery(cq);

		List<CourseDTO> list = tq.getResultList();

		CourseDTO dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}
		return dto;
	}

	public List search(CourseDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<CourseDTO> cq = builder.createQuery(CourseDTO.class);

		Root<CourseDTO> qRoot = cq.from(CourseDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {

			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				predicateList.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
			}
			if (dto.getDuration() != null && dto.getDuration().length() > 0) {
				predicateList.add(builder.like(qRoot.get("duration"), dto.getDuration() + "%"));
			}
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<CourseDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<CourseDTO> list = tq.getResultList();

		return list;
	}

}

package com.sunilos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.dto.CourseDTO;

@Repository
public class CourseDAOImpl extends BaseDAOImpl<CourseDTO> implements CourseDAOInt  {

	@Override
	protected List<Predicate> getWhereClause(CourseDTO dto, CriteriaBuilder builder, Root<CourseDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> wherecondition= new ArrayList<Predicate>();
		if(!isEmptyString(dto.getCoursename())){
			wherecondition.add(builder.like(qRoot.get("coursename"), dto.getCoursename()+"%"));
		}
		if(!isEmptyString(dto.getDuration())){
			wherecondition.add(builder.like(qRoot.get("duration"), dto.getDuration()+"%"));
		}
		
		return wherecondition;
	}

	@Override
	public Class<CourseDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CourseDTO.class;
	}
	
	

}

package com.sunilos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.common.BaseDAOInt;
import com.sunilos.common.UserContext;
import com.sunilos.dto.CourseDTO;
import com.sunilos.dto.SubjectDTO;
import com.sunilos.service.CourseServiceInt;

@Repository
public class SubjectDAOImpl extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt {

	@Autowired
	CourseDAOInt coursedaoint;
	@Override
	protected List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder builder, Root<SubjectDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> wherecondition= new ArrayList<Predicate>();
		if(!isEmptyString(dto.getSubjectName())){
			wherecondition.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName()+"%"));
		}
		
		
		return wherecondition;
	}

	@Override
	public Class<SubjectDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return SubjectDTO.class;
	}

	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		
		CourseDTO cdto = coursedaoint.findByPK(dto.getCourseId(), userContext);
		if(cdto != null) {
		dto.setCourseName(cdto.getCoursename());
		}
		
	}
  


}


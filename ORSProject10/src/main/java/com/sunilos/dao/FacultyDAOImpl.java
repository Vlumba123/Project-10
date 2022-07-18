package com.sunilos.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.dto.FacultyDTO;

@Repository
public class FacultyDAOImpl extends BaseDAOImpl<FacultyDTO> implements FacultyDAOInt {

	@Override
	protected List<Predicate> getWhereClause(FacultyDTO dto, CriteriaBuilder builder, Root<FacultyDTO> qRoot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<FacultyDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return FacultyDTO.class;
	}

}

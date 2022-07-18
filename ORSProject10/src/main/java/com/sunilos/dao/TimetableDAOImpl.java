package com.sunilos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.common.BaseDAOImpl;
import com.sunilos.common.UserContext;
import com.sunilos.dto.CollegeDTO;
import com.sunilos.dto.CourseDTO;
import com.sunilos.dto.RoleDTO;
import com.sunilos.dto.StudentDTO;
import com.sunilos.dto.SubjectDTO;
import com.sunilos.dto.TimetableDTO;

@Repository
public class TimetableDAOImpl extends BaseDAOImpl<TimetableDTO> implements TimetableDAOInt {

	@Override
	protected List<Predicate> getWhereClause(TimetableDTO dto, CriteriaBuilder builder, Root<TimetableDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();
		System.out.println("timetable DAO impl getwhereclause------------"+dto.getCourseName()+dto.getCourseId());
		
		 if (isNotNull(dto.getCourseId())) {
			 
			  whereCondition.add(builder.equal(qRoot.get("courseId"), dto.getCourseId()));
		 }
		 if (isNotNull(dto.getSubjectId())) {
			 
			 whereCondition.add(builder.equal(qRoot.get("subjectId"),dto.getSubjectId())); }

		/*
		 * if (!isNotNull(dto.getCourseId())) {
		 * 
		 * whereCondition.add(builder.equal(qRoot.get("courseId"), dto.getCourseId()));
		 * }
		 * 
		 * 
		 * if (!isNotNull(dto.getSubjectId())) {
		 * 
		 * whereCondition.add(builder.equal(qRoot.get("subjectId"),
		 * dto.getSubjectId())); }
		 * 
		 * if (!isEmptyString(dto.getExamTime())) {
		 * 
		 * whereCondition.add(builder.like(qRoot.get("examTime"), dto.getExamTime() +
		 * "%")); }
		 * 
		 * if (isNotNull(dto.getExamDate())) {
		 * 
		 * whereCondition.add(builder.equal(qRoot.get("examDate"), dto.getExamDate()));
		 * }
		 */

		return whereCondition;

	}

	@Override
	public Class<TimetableDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return TimetableDTO.class;
	}

	@Autowired
	CourseDAOInt courseService = null;
	@Autowired
	SubjectDAOInt subjectservice = null;

	@Override
	protected void populate(TimetableDTO dto, UserContext userContext) {
		System.out
				.println("inside timetable daoimpl populate method---------------------------------------------------");
		System.out.println("courseid= " + dto.getCourseId() + " subjectid= " + dto.getSubjectId());
		CourseDTO cDTO = courseService.findByPK(dto.getCourseId(), userContext);
		SubjectDTO sDTO = subjectservice.findByPK(dto.getSubjectId(), userContext);
		System.out.println("coursename=" + cDTO.getCoursename() + " subjectname" + sDTO.getSubjectName());
		if (cDTO != null || sDTO != null) {
			System.out.println("coursename=" + cDTO.getCoursename() + " subjectname" + sDTO.getSubjectName());
			dto.setCourseName(cDTO.getCoursename());
			dto.setSubjectName(sDTO.getSubjectName());

		}
	}

}

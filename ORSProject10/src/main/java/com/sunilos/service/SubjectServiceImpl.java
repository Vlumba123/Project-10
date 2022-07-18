package com.sunilos.service;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.dao.SubjectDAOInt;
import com.sunilos.dto.SubjectDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SubjectServiceImpl extends BaseServiceImpl<SubjectDTO, SubjectDAOInt> implements SubjectServiceInt {

}

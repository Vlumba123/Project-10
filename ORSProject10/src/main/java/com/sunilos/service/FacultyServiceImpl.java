package com.sunilos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseDAOInt;
import com.sunilos.common.BaseServiceImpl;
import com.sunilos.dao.FacultyDAOInt;
import com.sunilos.dto.FacultyDTO;

@Service
@Transactional
public class FacultyServiceImpl extends BaseServiceImpl<FacultyDTO, FacultyDAOInt> implements FacultyServiceInt {

}

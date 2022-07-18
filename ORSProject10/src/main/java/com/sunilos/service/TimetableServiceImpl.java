package com.sunilos.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.common.BaseServiceImpl;
import com.sunilos.dao.TimetableDAOInt;
import com.sunilos.dto.TimetableDTO;


@Service
@Transactional
public class TimetableServiceImpl extends BaseServiceImpl<TimetableDTO, TimetableDAOInt> implements TimetableServiceInt {
	
	

}

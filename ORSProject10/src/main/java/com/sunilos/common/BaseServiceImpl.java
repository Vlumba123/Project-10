package com.sunilos.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.dto.CourseDTO;
import com.sunilos.exception.DatabaseException;
import com.sunilos.exception.DuplicateRecordException;

public abstract class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> {

	private static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

	@Autowired
	protected D baseDao;

	@Transactional(readOnly = true)
	public T findById(long id, UserContext userContext) {
		T dto = baseDao.findByPK(id, userContext);
		// T dto baseDao.findByPK(Class<T>, pk)
		return dto;
	}

	@Transactional(readOnly = true)
	public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext) {
		return baseDao.search(dto, pageNo, pageSize, userContext);
	}

	@Transactional(readOnly = true)
	public List<T> search(T dto, UserContext userContext) {
		System.out.println("inside BaseServiceImpl search method-----------------");
		return baseDao.search(dto, userContext);
	}

	@Transactional(readOnly = false)
	public long add(T dto, UserContext userContext) throws DuplicateRecordException {
		System.out.println("inside BaseSrviceImpl add--------");
		// check duplicate
		long pk = baseDao.add(dto, userContext);
		return pk;
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) throws DuplicateRecordException {
		baseDao.update(dto, userContext);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto, UserContext userContext) throws DuplicateRecordException {
		System.out.println("inside BaseSrviceImpl Save--------");
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto, userContext);
		} else {
			id = add(dto, userContext);
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T delete(long id, UserContext userContext) {
		System.out.println(" (1) inside BaseserviceImpl delete--------id="+id);
		log.debug("Role Service delete Start");
		T dto = findById(id, userContext);
		if (dto == null) {
			System.out.println("inside BaseserviceImpl dto == null condition--------");
			throw new DatabaseException("Record not found");
		}
		System.out.println(" (2) inside BaseserviceImpl basedao delete method called--------id="+id);
		baseDao.delete(dto, userContext);
		log.debug("Role Service delete End");
		return dto;
	}

}

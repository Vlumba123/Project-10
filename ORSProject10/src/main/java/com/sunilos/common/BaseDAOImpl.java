package com.sunilos.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sunilos.dto.CourseDTO;
import com.sunilos.exception.DuplicateRecordException;

public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		// this.sessionFactory =
		// entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
	}

	/**
	 * Find record by Unique key
	 * 
	 * @param attribute
	 * @param val
	 * @param dtoClass
	 * @return
	 */
	public T findByUniqueKey(String attribute, Object val, UserContext userContext) {
		
		
		System.out.println("inside findByUniqueKey string attri----------------"+attribute);

		Class<T> dtoClass = getDTOClass();
		System.out.println("this is dto class"+dtoClass);

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		System.out.println("this is builder------------------"+builder.toString());

		CriteriaQuery<T> cq = builder.createQuery(dtoClass);
		System.out.println("this is cq------------------"+cq.hashCode());
		Root<T> qRoot = cq.from(dtoClass);
		System.out.println("this is qRoot------------------"+qRoot.toString());
		Predicate condition = builder.equal(qRoot.get(attribute), val);
		System.out.println("this is condition------------------"+condition.toString());
		if (userContext != null && !isZeroNumber(userContext.getOrgId())) {
			System.out.println("inside usercontext condition");
			Predicate conditionGrp = builder.equal(qRoot.get("orgId"), userContext.getOrgId());
			cq.where(condition, conditionGrp);
		} else {
			cq.where(condition);
		}

		TypedQuery<T> query = entityManager.createQuery(cq);
		System.out.println("this is query-----"+query);

		List<T> list = query.getResultList();
		System.out.println("this is after list-----aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa    ");

		T dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
			System.out.println("this is after dto got-----aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+dto.getId());
		}
		
  
		System.out.println("before retun");
		return dto;

	}

	public T findByPK(long pk, UserContext userContext) {
		System.out.println("inside BaseDAOImpl findbyPK called"+pk);
		T dto = entityManager.find(getDTOClass(), pk);
		System.out.println("inside BaseDAOImpl findbyPK called");
		return dto;
	}

	/**
	 * Build criteria query
	 * 
	 * @param dto
	 * @return
	 */
	protected TypedQuery<T> createCriteria(T dto, UserContext userContext) {
		
        System.out.println("step---------1");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        System.out.println("step---------2"+builder.toString());
		// Create criteria
		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());
        System.out.println("step---------3"+cq.toString());
		// Columns information
		Root<T> qRoot = cq.from(getDTOClass());
        System.out.println("step---------4"+qRoot.toString());
		// Column of query
		cq.select(qRoot);
        System.out.println("step---------5");
		// Create where conditions
		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);
        System.out.println("step---------6"+whereClause.size());
		// Put organization filter
		if (dto.isGroupFilter()) {
	        System.out.println("step---------7");
			whereClause.add(builder.equal(qRoot.get("orgId"), dto.getOrgId()));
		}
        System.out.println("step---------8");
		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));
        System.out.println("step---------9");
		List<Order> orderBys = getOrderByClause(dto, builder, qRoot);
        System.out.println("step---------10");
		cq.orderBy(orderBys.toArray(new Order[orderBys.size()]));
        System.out.println("step---------11");
		TypedQuery<T> query = entityManager.createQuery(cq);
        System.out.println("step---------12");
		return query;

	}

	/**
	 * Creates WHERE clause of search
	 * 
	 * @param dto
	 * @param builder
	 * @param qRoot
	 * @return
	 */
	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {
		System.out.println("inside BaseDAOImpl search method with zerozero 1-----------------"+dto+userContext);
		
		TypedQuery<T> query = createCriteria(dto, userContext);
		
		System.out.println(" PAGE ->>>>>>>>>>>>>>>>" + pageNo + " --- " + pageSize);
		if (pageSize > 0) {
			
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}
		System.out.println("inside BaseDAOImpl search method with zerozero 2-----------------");
		List list = query.getResultList();
        System.out.println("list--------"+list.size());
		return list;
	}

	public List search(T dto, UserContext userContext) {
		System.out.println("inside BaseDAOImpl search method-----------------");
		return search(dto, 0, 0, userContext);
	}

	/**
	 * Run HQL query
	 * 
	 * @param hql
	 * @param userContext
	 * @return
	 */
	public List runHQL(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		List l = q.getResultList();
		return l;
	}

	/**
	 * Add a record
	 */
	public long add(T dto, UserContext userContext) {
		System.out.println("inside BaseDAOImpl add--------");
		checkDuplicate(dto, userContext);

		dto.setCreatedBy(userContext.getLoginId());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		dto.setOrgId(userContext.getOrgId());
		dto.setOrgName(userContext.getOrgName());
		

		populate(dto,userContext);

		entityManager.persist(dto);

		return dto.getId();

	}

	/**
	 * Populate redundant values into dto. Overridden by chiled classes.
	 * 
	 * @param dto
	 */
	protected void populate(T dto, UserContext userContext) {

	}

	/**
	 * Update a record
	 */
	public void update(T dto, UserContext userContext) {
		checkDuplicate(dto, userContext);

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.merge(dto);
	}

	/**
	 * Check unique keys
	 * 
	 * @param dto
	 */
	private void checkDuplicate(T dto, UserContext userContext) {
		LinkedHashMap<String, Object> uniqueKeys = dto.uniqueKeys();
		if (uniqueKeys == null) {
			return;
		}
		uniqueKeys.forEach((key, value) -> {
			T dtoExist = findByUniqueKey(key, value, userContext);
			if (dtoExist != null && dto.getId() != dtoExist.getId()) {
				throw new DuplicateRecordException(key + " already exists");
			}
		});
	}

	/**
	 * Delete a record
	 */
	public void delete(T dto, UserContext userContext) {
		System.out.println(" (1) inside BaseDAOImpl delete--------id="+dto.getId());
		entityManager.remove(dto);
		
	}

	/**
	 * Get DTO Class object
	 * 
	 * @return
	 */
	public abstract Class<T> getDTOClass();

	/**
	 * Check empty string
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isZeroNumber(Double val) {
		return val == null || val == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */

	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}

	protected boolean isNotNull(Object val) {
		return val != null;
	}

	/**
	 * Get order by clause
	 * 
	 * @param dto
	 * @param builder
	 * @param qRoot
	 * @return
	 */
	protected List<Order> getOrderByClause(T dto, CriteriaBuilder builder, Root<T> qRoot) {

		// Apply Order by clause

		LinkedHashMap<String, String> map = dto.orderBY();

		List<Order> orderBys = new ArrayList<Order>();

		map.forEach((key, value) -> {
			if (value.equals("asc")) {
				orderBys.add(builder.asc(qRoot.get(key)));
			} else {
				orderBys.add(builder.desc(qRoot.get(key)));
			}
		});

		return orderBys;
	}

}

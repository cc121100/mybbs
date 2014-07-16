package org.mybbs.base.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> 
		extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	
	private static Logger logger = Logger.getLogger(BaseRepositoryImpl.class);
	
	private final EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		//super(domainClass, em);
		this(JpaEntityInformationSupport.getMetadata(domainClass, entityManager), entityManager); 
	}
	
	
	

}

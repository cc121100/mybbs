package org.mybbs.base.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class BaseRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	private static Logger logger = Logger.getLogger(BaseRepositoryImpl.class);

	protected RepositoryFactorySupport createRepositoryFactory(
			EntityManager entityManager) {
		
		return new BaseRepositoryFactory(entityManager);
	}

}

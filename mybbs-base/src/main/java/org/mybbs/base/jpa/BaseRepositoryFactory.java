package org.mybbs.base.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;

public class BaseRepositoryFactory extends JpaRepositoryFactory {
	
	private final EntityManager entityManager;

	public BaseRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	@Override
	protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
			RepositoryMetadata metadata, EntityManager entityManager) {
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		return new BaseRepositoryImpl(entityInformation, entityManager); 
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return BaseRepositoryImpl.class;
	}
	
	

}

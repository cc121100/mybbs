package org.mybbs.base.dao;

import org.mybbs.base.jpa.BaseRepository;
import org.mybbs.base.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterDAO extends BaseRepository<Filter, String> {
	
	
}

package org.mybbs.base.dao;

import java.util.List;

import org.mybbs.base.jpa.BaseRepository;
import org.mybbs.base.model.SourcePage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SourcePageDAO extends BaseRepository<SourcePage, String> {
	
	
	@Query("SELECT sp FROM SourcePage sp WHERE sp.status =:status AND sp.category = :category")
	public List<SourcePage> getSourcePagesByStatusAndCategory(@Param("status") String status, @Param("category") String category);
	
	
	@Query("SELECT sp FROM SourcePage sp WHERE sp.sampleSPId = :sample_sp_id")
	public List<SourcePage> getAllSubSPForSampleSP(@Param("sample_sp_id") String sample_sp_id);
	
}

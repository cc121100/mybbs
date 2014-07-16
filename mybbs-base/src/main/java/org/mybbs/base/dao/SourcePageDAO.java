package org.mybbs.base.dao;

import java.util.List;

import org.mybbs.base.jpa.BaseRepository;
import org.mybbs.base.model.SourcePage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SourcePageDAO extends BaseRepository<SourcePage, String> {
	
	/*public final String getSubscribedSPQuery = 
			"SELECT sp.id from tbl_source_page sp where sp.sp_status = 'A' AND sp.category ='T' AND sp.id IN (select distinct source_page_id from tbl_setting_to_source_page)";
	public final String str1 = 
			"SELECT * from tbl_source_page";*/
	
	@Query("SELECT sp FROM SourcePage sp WHERE sp.status =:status AND sp.category = :category")
	public List<SourcePage> getSourcePagesByStatusAndCategory(@Param("status") String status, @Param("category") String category);
	
	/*@Query(value="str1")
	public List<SourcePage> getSubscribedSP(List<String> spIds);*/
	
	@Query("SELECT sp FROM SourcePage sp where sp.sample_sp_id = :sample_sp_id")
	public List<SourcePage> getAllSubSPForSampleSP(@Param("sample_sp_id") String sample_sp_id);
	
}

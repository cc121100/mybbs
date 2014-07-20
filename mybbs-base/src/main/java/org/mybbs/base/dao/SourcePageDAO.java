package org.mybbs.base.dao;

import java.util.List;

import org.mybbs.base.jpa.BaseRepository;
import org.mybbs.base.model.SourcePage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SourcePageDAO extends BaseRepository<SourcePage, String> {
	
	
	static String GETDEFAULTSPSQL = "SELECT DISTINCT SP.* FROM TBL_SOURCE_PAGE SP where sp.id in" +
			"(select ussp.SOURCE_PAGE_ID from TBL_SETTING_TO_SOURCE_PAGE ussp where ussp.user_setting_id in" +
			" (select us.id from tbl_user_setting us where us.category='D'))";
	
	@Query("SELECT sp FROM SourcePage sp WHERE sp.status =:status AND sp.category = :category")
	public List<SourcePage> getSourcePagesByStatusAndCategory(@Param("status") String status, @Param("category") String category);
	
	
	@Query("SELECT sp FROM SourcePage sp WHERE sp.category = :category AND sp.sourcePageSampleId = :sample_sp_id")
	public List<SourcePage> getAllSubSPForSampleSP(@Param("sample_sp_id") String sample_sp_id, @Param("category") String category);
	
	//@Query(value="SELECT DISTINCT SP.* FROM TBL_SOURCE_PAGE SP WHERE SP.ID IN (SELECT USSP.SOURCE_PAGE_ID FROM TBL_SETTING_TO_SOURCE_PAGE USSP)", nativeQuery = true)
	@Query(value="SELECT DISTINCT SP.* FROM TBL_SOURCE_PAGE SP INNER JOIN TBL_SETTING_TO_SOURCE_PAGE USSP ON SP.ID = USSP.SOURCE_PAGE_ID", nativeQuery = true)
	public List<SourcePage> getAllSubscibedSP();
	
	
	@Query(value=GETDEFAULTSPSQL, nativeQuery = true)
	public List<SourcePage> getDefaultSP();
}

package org.mybbs.base.dao;

import java.util.List;

import org.mybbs.base.jpa.BaseRepository;
import org.mybbs.base.model.UserSetting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSettingDAO extends BaseRepository<UserSetting, String>{

	@Query("SELECT us FROM UserSetting us WHERE us.category = :category")
	public List<UserSetting> getUserSettingByCategory(@Param("category")String category);
}

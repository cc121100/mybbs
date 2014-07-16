package org.cc.mybbs.batchjobs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.dao.SourcePageDAO;
import org.mybbs.base.dao.UserSettingDAO;
import org.mybbs.base.model.SourcePage;
import org.mybbs.base.model.UserSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoadSourcePageService {
	
	Logger logger = Logger.getLogger(LoadSourcePageService.class);

	@Autowired
	SourcePageDAO sourcePageDAO;
	
	@Autowired
	UserSettingDAO userSettingDAO;
	
	
	/*public List<SourcePage> loadFromDB(){
		logger.info("Load active source page from db");
		
		List<SourcePage> sourcePageList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_T);
		logger.info("Successed load active sourcePage list from db, size:" + sourcePageList.size());
		return  sourcePageList;
	}*/
	
	@Transactional
	public List<SourcePage> loadActiveSPFromDB(){
		
		try{
			// 1. load active sample SP 
			logger.info("--- loadActiveSPFromDB ---");
			logger.info("Load smaple source page from db");
			List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_S);
			lazyLoadSPFilters(sampleSPList);
			
			// 2. load active target SP
			logger.info("Load target source page from db");
			List<SourcePage> targetSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_T);
			
			// 3. generate active target SP
			List<SourcePage> resultSPList = generateTargetSP(sampleSPList, targetSPList);
			
			return resultSPList;
		}catch(Exception e){
			logger.error("Error occurs when loadActiveSPFromDB :", e);
			return null;
		}
		
	}
	
	@Transactional
	public List<SourcePage> loadSubscribedSPFromDB(){
		
		try{
			// 1. load active sample SP 
			logger.info("--- loadSubscribedSPFromDB ---");
			logger.info("Load smaple source page from db");
			List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_S);
			lazyLoadSPFilters(sampleSPList);
			
			// 2. Load Subscribed SP
			logger.info("Load target source page from db");
			List<UserSetting> usList = userSettingDAO.getUserSettingByCategory(BaseConstants.US_CATEGORY_U);
			Set<SourcePage> targetSPSet = new HashSet<SourcePage>();
			for(UserSetting us : usList){
				if(us.getSourcePages() != null && us.getSourcePages().size() > 0){
					targetSPSet.addAll(us.getSourcePages());
				}
			}
			
			// 3. generate active target SP
			List<SourcePage> resultSPList = generateTargetSP(sampleSPList, targetSPSet);
			return resultSPList;
			
		}catch(Exception e){
			logger.error("Error occurs when loadSubscribedSPFromDB :", e);
			return null;
		}
	}
	
	@Transactional
	public List<SourcePage> loadLoginedAndDefaultSPFromDB(){
		return null;
	}
	
	
	private List<SourcePage> generateTargetSP(List<SourcePage> sampleSPList,Collection<SourcePage> targetSPList){
		
		List<SourcePage> resultList = new ArrayList<SourcePage>();
		if(sampleSPList == null || sampleSPList.size() < 1
				|| targetSPList == null || targetSPList.size() < 1){
			return resultList;
		}
		
		SourcePage targetSP = null;
		
		Iterator<SourcePage> it = targetSPList.iterator();
		while(it.hasNext()){
			targetSP = it.next();
			
			if(StringUtils.isNotEmpty(targetSP.getSampleSPId())){
				
				for(int j = 0; j < sampleSPList.size(); j++){
					if(targetSP.getSampleSPId().equals(sampleSPList.get(j).getId())){
						targetSP.setSampleSourcePage(sampleSPList.get(j));
						resultList.add(targetSP);
						break;
					}
				}
			}
		}
		
		return resultList;
	}
	
	
	private void lazyLoadSPFilters(List<SourcePage> spList){
		for(SourcePage sp : spList){
			sp.getSourcePageFilters().size();
		}
	}
}

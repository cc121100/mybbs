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
import org.cc.mybbs.batchjobs.utils.LazyLoadUtils;
import org.cc.mybbs.batchjobs.utils.SPSamplePool;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.dao.SourcePageDAO;
import org.mybbs.base.dao.SourcePageSampleDAO;
import org.mybbs.base.dao.UserSettingDAO;
import org.mybbs.base.model.SourcePage;
import org.mybbs.base.model.SourcePageSample;
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
	
	@Autowired
	SourcePageSampleDAO sourcePageSampleDAO;
	
	@Transactional
	public List<SourcePage> loadNavicateSPFromDB(){
		
		try{
			// 1. load active sample SP 
			logger.info("--- loadNavicateSPFromDB ---");
			logger.info("Load smaple source page from db");
			//List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_S);
			List<SourcePageSample> spsList = getSPSList();
			
			// 2. load active navicate SP
			logger.info("Load navicate source page from db");
			List<SourcePage> targetSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_N);
			
			// 3. generate active navicate SP
			List<SourcePage> resultSPList = generateTargetSP(spsList, targetSPList);
			
			return resultSPList;
		}catch(Exception e){
			logger.error("Error occurs when loadNavicateSPFromDB :", e);
			return null;
		}
		
	}
	
	@Transactional
	public List<SourcePage> loadActiveSPFromDB(){
		
		try{
			// 1. load active sample SP 
			logger.info("--- loadActiveSPFromDB ---");
			logger.info("Load smaple source page from db");
			//List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_S);
			
			List<SourcePageSample> spsList = getSPSList();
			
			// 2. load active target SP
			logger.info("Load active source page from db");
			List<SourcePage> targetSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_T);
			
			// 3. generate active target SP
			List<SourcePage> resultSPList = generateTargetSP(spsList, targetSPList);
			
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
			//List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_S);
			List<SourcePageSample> spsList = getSPSList();
			
			// 2. Load Subscribed SP
			logger.info("Load Subscribed source page from db");
			/*List<UserSetting> usList = userSettingDAO.getUserSettingByCategory(BaseConstants.US_CATEGORY_U);
			Set<SourcePage> targetSPSet = new HashSet<SourcePage>();
			for(UserSetting us : usList){
				if(us.getSourcePages() != null && us.getSourcePages().size() > 0){
					targetSPSet.addAll(us.getSourcePages());
				}
			}*/
			
			List<SourcePage> spList = sourcePageDAO.getAllSubscibedSP();
			
			// 3. generate active target SP
			List<SourcePage> resultSPList = generateTargetSP(spsList, spList);
			return resultSPList;
			
		}catch(Exception e){
			logger.error("Error occurs when loadSubscribedSPFromDB :", e);
			return null;
		}
	}
	
	@Transactional
	public List<SourcePage> loadLoginedAndDefaultSPFromDB(){
		try{
			// 1. load active sample SP 
			logger.info("--- loadLoginedAndDefaultSPFromDB ---");
			logger.info("Load smaple source page from db");
			//List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_CATEGORY_S);
			List<SourcePageSample> spsList = getSPSList();
			
			// 2. Load Subscribed SP
			logger.info("Load target source page from db");
			/*List<UserSetting> usList = userSettingDAO.getUserSettingByCategory(BaseConstants.US_CATEGORY_D);
			Set<SourcePage> targetSPSet = new HashSet<SourcePage>();
			for(UserSetting us : usList){
				if(us.getSourcePages() != null && us.getSourcePages().size() > 0){
					targetSPSet.addAll(us.getSourcePages());
				}
			}*/
			
			
			List<SourcePage> spList = new ArrayList<SourcePage>();
			List<SourcePage> defaultSPList = sourcePageDAO.getDefaultSP();
			spList.addAll(defaultSPList);
			
			// TODO 3. get all login usersettings' sourcepage
			
			
			// 3. generate active target SP
			List<SourcePage> resultSPList = generateTargetSP(spsList, spList);
			return resultSPList;
			
		}catch(Exception e){
			logger.error("Error occurs when loadSubscribedSPFromDB :", e);
			return null;
		}
	}
	
	
	private List<SourcePage> generateTargetSP(List<SourcePageSample> spsList,Collection<SourcePage> targetSPList){
		
		List<SourcePage> resultList = new ArrayList<SourcePage>();
		if(spsList == null || spsList.size() < 1
				|| targetSPList == null || targetSPList.size() < 1){
			return resultList;
		}
		
		SourcePage targetSP = null;
		
		Iterator<SourcePage> it = targetSPList.iterator();
		while(it.hasNext()){
			targetSP = it.next();
			
			if(StringUtils.isNotEmpty(targetSP.getSourcePageSampleId())){
				
				for(int j = 0; j < spsList.size(); j++){
					if(targetSP.getSourcePageSampleId().equals(spsList.get(j).getId())){
						targetSP.setSpSample(spsList.get(j));
						resultList.add(targetSP);
						break;
					}
				}
			}
		}
		
		return resultList;
	}
	
	
	/*private void lazyLoadSPFilters(List<SourcePageSample> spsList){
		for(SourcePageSample sps : spsList){
			sps.getSourcePageFilters().size();
		}
	}*/
	
	private List<SourcePageSample> getSPSList(){
		List<SourcePageSample> spsList = SPSamplePool.getSpsList();
		if(spsList == null || spsList.size() < 1){
			spsList = sourcePageSampleDAO.findAll();
			LazyLoadUtils.lazyLoadSPFilters(spsList);
		}
		return spsList;
	}
}

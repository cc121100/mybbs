package org.cc.mybbs.batchjobs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.dao.SourcePageDAO;
import org.mybbs.base.model.SourcePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadSourcePageService {
	
	Logger logger = Logger.getLogger(LoadSourcePageService.class);

	@Autowired
	SourcePageDAO sourcePageDAO;
	
	
	public List<SourcePage> loadFromDB(){
		logger.info("Load active source page from db");
		
		List<SourcePage> sourcePageList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_Category_T);
		logger.info("Successed load active sourcePage list from db, size:" + sourcePageList.size());
		return  sourcePageList;
	}
	
	public List<SourcePage> loadActiveSPFromDB(){
		
		// 1. load active sample SP 
		logger.info("Load smaple source page from db");
		List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_Category_S);
		
		// 2. load active target SP
		logger.info("Load target source page from db");
		List<SourcePage> targetSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_Category_T);
		
		// 3. generate active target SP
		List<SourcePage> resultSPList = generateTargetSP(sampleSPList, targetSPList);
		
		return resultSPList;
	}
	
	public List<SourcePage> loadSubscribedSPFromDB(){
		// 1. load active sample SP 
		logger.info("Load smaple source page from db");
		List<SourcePage> sampleSPList = sourcePageDAO.getSourcePagesByStatusAndCategory(BaseConstants.STATUA_A,BaseConstants.SP_Category_S);
		
		// 2. Load Subscribed SP
		
		
		return null;
	}
	
	public List<SourcePage> loadLoginedAndDefaultSPFromDB(){
		return null;
	}
	
	
	private List<SourcePage> generateTargetSP(List<SourcePage> sampleSPList,List<SourcePage> targetSPList){
		
		List<SourcePage> resultList = new ArrayList<SourcePage>();
		if(sampleSPList == null || sampleSPList.size() < 1
				|| targetSPList == null || targetSPList.size() < 1){
			return resultList;
		}
		
		SourcePage targetSP = null;
		for(int i = 0; i < targetSPList.size(); i++){
			targetSP = targetSPList.get(i);
			
			if(targetSP.getSampleSourcePage() != null){
				
				for(int j = 0; j < sampleSPList.size(); j++){
					if(targetSP.getSampleSourcePage().getId().equals(sampleSPList.get(j).getId())){
						targetSP.setSampleSourcePage(sampleSPList.get(j));
						resultList.add(targetSP);
						break;
					}
				}
			}
		}
		
		return resultList;
	}
	
}

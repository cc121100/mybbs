package org.cc.mybbs.batchjobs.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.dao.SourcePageDAO;
import org.mybbs.base.model.SourcePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateSubSPForNaviSPService {
	
	Logger logger = Logger.getLogger(UpdateSubSPForNaviSPService.class);

	@Autowired
	SourcePageDAO sourcePageDAO;

	@Transactional
	public void update(SourcePage sourcePage){
		try{
			logger.info("Start update sub source pages for sample source page:" + sourcePage.getId());
			
			/** 1. if first time update sub source pages for for sample source page, 
			 * 		it means sourcePageDAO.getAllSubSPForSampleSP(sourcePage.getId()) is null or size < 1
			 *		add all the sub source pages 
			*/
			
			List<SourcePage> oldSubSPList = sourcePageDAO.getAllSubSPForSampleSP(sourcePage.getSampleSPIdForNaviSP(),BaseConstants.SP_CATEGORY_T);
			List<SourcePage> newSubSPList =  generateNewSubSPList(sourcePage);
			
			if(oldSubSPList == null || oldSubSPList.size() < 1){
				
				logger.info("First time update sub source pages for source page :" + sourcePage.getId());
				sourcePageDAO.save(newSubSPList);
				sourcePageDAO.flush();
				logger.info("Successfully add new sub source pages, with count " + newSubSPList.size());
				
			}else{
				
				/**
				 * 	2. if not first time
				 * 		subSP.uniqueLabel = sourcePage.uniqueLabel + "-" + subSP.targetPageName, uniqueLabel is unupdated
				 * 		loop subSPList , update the subSp's targetUrl, if not match, by uniqueLabel
				 * 				delete not exsited subSP
				 * 				add new subSP
				 */
				
				logger.info("Update sub source pages for source page :" + sourcePage.getId());
				SourcePage oldSP = null;
				SourcePage newSP = null;
				boolean isExsitedSP = false;
				
				Iterator<SourcePage> it = oldSubSPList.iterator();
				while(it.hasNext()){
					isExsitedSP = false;
					oldSP = it.next();
					for(int j = 0; j < newSubSPList.size(); j++){
						newSP = newSubSPList.get(j);
						if(oldSP.getUniqueLabel().equals(newSP.getUniqueLabel())){
							if(!oldSP.getTargetPageName().equals(newSP.getTargetPageName())){
								oldSP.setTargetPageName(newSP.getTargetPageName());
							}
							if(!oldSP.getTargetPageUrl().equals(newSP.getTargetPageUrl())){
								oldSP.setTargetPageUrl(newSP.getTargetPageUrl());
							}
							if(!oldSP.getDomainName().equals(newSP.getDomainName())){
								oldSP.setDomainName(newSP.getDomainName());
							}
							
							isExsitedSP = true;
							break;
						}
						
					}
					
					if(!isExsitedSP){
						it.remove();
					}
				}
				
				for(int i = 0; i < newSubSPList.size(); i++){
					isExsitedSP = false;
					newSP = newSubSPList.get(i);
					for(int j = 0;j < oldSubSPList.size(); j++){
						oldSP = oldSubSPList.get(j);
						if(newSP.getUniqueLabel().equals(oldSP.getUniqueLabel())){
							isExsitedSP = true;
							break;
						}
					}
					
					if(!isExsitedSP){
						oldSubSPList.add(newSP);
					}
				}
				
				sourcePageDAO.save(oldSubSPList);
				sourcePageDAO.flush();
				logger.info("Successfully update sub source pages, with count " + oldSubSPList.size());
				
			}
			
		}catch(Exception e){
			logger.error("Error occurs when update sub source pages for sample source page, " +e);
		}
		
	}
	
	private List<SourcePage> generateNewSubSPList(SourcePage navicateSP){
		
		List<SourcePage> newSubSPList = new ArrayList<SourcePage>();
		SourcePage newSubSP = null;
		for(Entry<String, String> entry : navicateSP.getUrlAndContentMap().entrySet()){
			newSubSP = new SourcePage();
			newSubSP.setTargetPageName(navicateSP.getUniqueLabel() + " - " + entry.getValue());
			newSubSP.setTargetPageUrl(navicateSP.getDomainName()  + entry.getKey());
			newSubSP.setUniqueLabel(navicateSP.getUniqueLabel() + " - " + entry.getValue());
			newSubSP.setStatus(BaseConstants.STATUA_A);
			newSubSP.setCategory(BaseConstants.SP_CATEGORY_T);
			if(StringUtils.isEmpty(navicateSP.getSubSPDomainName())){
				newSubSP.setDomainName(navicateSP.getDomainName());
			}else{
				newSubSP.setDomainName(navicateSP.getSubSPDomainName());
			}
			newSubSP.setSourcePageSampleId(navicateSP.getSampleSPIdForNaviSP());
			
			newSubSPList.add(newSubSP);
		}
		
		return newSubSPList;
	}
}

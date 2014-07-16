package org.cc.mybbs.batchjobs.service;

import java.util.ArrayList;
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
			// 1 delete all sub sp for the sample sp
			logger.info("Start update sub source pages for sample source page:" + sourcePage.getId());
			List<SourcePage> oldSubSPList = sourcePageDAO.getAllSubSPForSampleSP(sourcePage.getId());
			
			String domainName = null;
			if(oldSubSPList != null && oldSubSPList.size() > 0){
				domainName = oldSubSPList.get(0).getDomainName();
			}
			
			logger.info("	Delete old sub source pages for sample source page:" + sourcePage.getId());
			sourcePageDAO.deleteInBatch((Iterable<SourcePage>) oldSubSPList.iterator());
			
			// 2 add new sub sp for the smple sp
			logger.info("	Add new sub source pages for sample source page:" + sourcePage.getId());
			
		}catch(Exception e){
			logger.error("Error occurs when update sub source pages for sample source page, " +e);
		}
		
		
		
		
	}
	
	private List<SourcePage> generateNewSubSPList(SourcePage sampleSP, String domainName){
		
		if(StringUtils.isEmpty(domainName)){
			return null;
		}
		
		List<SourcePage> newSubSPList = new ArrayList<SourcePage>();
		SourcePage newSubSP = null;
		for(Entry<String, String> entry : sampleSP.getUrlAndContentMap().entrySet()){
			newSubSP = new SourcePage();
			newSubSP.setTargetPageName(sampleSP.getTargetPageName() + " - " + entry.getValue());
			newSubSP.setTargetPageUrl(sampleSP.getDomainName() + "/" + entry.getKey());
			newSubSP.setStatus(BaseConstants.STATUA_A);
			newSubSP.setCategory(BaseConstants.SP_CATEGORY_T);
			newSubSP.setDomainName(domainName);
			newSubSP.setSampleSPId(sampleSP.getId());
		}
		
		return null;
	}
}

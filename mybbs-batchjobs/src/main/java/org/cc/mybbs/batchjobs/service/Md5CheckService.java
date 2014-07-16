package org.cc.mybbs.batchjobs.service;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybbs.base.dao.SourcePageDAO;
import org.mybbs.base.model.SourcePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Md5CheckService {
	
	private static Logger logger = Logger.getLogger(Md5CheckService.class);
	
	@Autowired
	private SourcePageDAO sourcePageDAO;
	
	@Transactional
	public SourcePage check(SourcePage sourcePage){
		logger.info("Check md5 code of sourcePage: " + sourcePage.getTargetPageName() + ", old md5 code is " + sourcePage.getMd5Code());
		
		String beforeStr = generate(sourcePage.getUrlAndContentMap());
		String afterString = DigestUtils.md5Hex(beforeStr);
		logger.info("Check md5 code of sourcePage: " + sourcePage.getTargetPageName() + ", new md5 code is " + afterString);
		
		// if sourcePage.getMd5Code() is empty, means it is the first time to generate the sourcePage
		// sourcePage.getMd5Code() != afterString, generate new file ,update md5Code in DB
		if(StringUtils.isEmpty(sourcePage.getMd5Code())
				|| StringUtils.isNotEmpty(sourcePage.getMd5Code()) && !afterString.equals(sourcePage.getMd5Code())){
			
			sourcePage.setMd5Code(afterString);
			sourcePageDAO.saveAndFlush(sourcePage);
			logger.info("update md5 code of sourcePage: " + sourcePage.getTargetPageName() + ", new md5 code is " + afterString);
			
			return sourcePage;
			
		}else{
			return null;
		}
	}
	
	public String generate(Map<String, String> map){
		StringBuffer sb = new StringBuffer();
		
		for(Entry<String, String> entry : map.entrySet()){
			sb.append(entry.getKey());
		}
		
		return sb.toString();
	}

}

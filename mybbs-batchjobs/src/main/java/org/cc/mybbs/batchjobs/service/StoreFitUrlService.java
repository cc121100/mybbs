package org.cc.mybbs.batchjobs.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.cc.mybbs.batchjobs.constants.BatchJobConstants;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.model.SourcePage;
import org.springframework.stereotype.Component;

@Component
public class StoreFitUrlService {
	
	private static Logger logger = Logger.getLogger(StoreFitUrlService.class);
	
	public void store(SourcePage sourcePage){
		
		logger.info("Start store urls for sourcePage: " + sourcePage.getTargetPageName());
		
		// 1 append <a href="key" target="_blank">value</a>, href needs sourcePage.domianName prefix
		List<String> hrefList = generateHyperlink(sourcePage);
		
		// store in file
		if(hrefList != null && hrefList.size() > 0){
			String pageFilePath = BatchJobConstants.URL_LOCATION + File.separator + sourcePage.getId() + BatchJobConstants.URL_FILE_SUFFIX;
			logger.info("Url file is " + pageFilePath);
			
			File file = new File(pageFilePath);
			if(!file.exists()){
				
			}
			try {
				FileUtils.writeLines(file, BaseConstants.ENCODING_UTF8, hrefList, false);
			} catch (IOException e) {
				logger.error("Error occurs when write urls to file, " + e);
				e.printStackTrace();
			}
			logger.info("End store urls for sourcePage: " + sourcePage.getTargetPageName() + " with " + hrefList.size() + " urls");
			
		}
	}
	
	public List<String> generateHyperlink(SourcePage sourcePage){
		
		List<String> list = new ArrayList<String>();
		StringBuffer sb = null;
		String href = null;
		
		for(Entry<String, String> entry : sourcePage.getUrlAndContentMap().entrySet()){
			href = sourcePage.getDomainName() + entry.getKey();
			
			sb = new StringBuffer("");
			sb.append("<a ")
				.append("href='").append(href).append("' ")
				.append("target='_blank'>")
				.append(entry.getValue())
				.append("</a>");
			
			list.add(sb.toString());
		}
		return list;
	}

}

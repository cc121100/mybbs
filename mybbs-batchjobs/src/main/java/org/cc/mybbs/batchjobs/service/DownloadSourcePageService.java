package org.cc.mybbs.batchjobs.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.cc.mybbs.batchjobs.constants.BatchJobConstants;
import org.cc.mybbs.batchjobs.utils.HttpUtils;
import org.mybbs.base.model.SourcePage;
import org.mybbs.base.utils.PropertyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DownloadSourcePageService {
	
	Logger logger = Logger.getLogger(DownloadSourcePageService.class);
	
	public SourcePage download(SourcePage sourcePage){
		
		//System.out.println("Download soure page, " + sourcePage.getTargetPageUrl());
		logger.info("Download soure page: " + sourcePage.getTargetPageUrl());
		
		byte[] response = HttpUtils.doGet(sourcePage.getTargetPageUrl(), null);
		if(response == null || response.length < 1){
			logger.error("Error occurs when download source page with url:" + sourcePage.getTargetPageUrl());
			return null;
		}
		
		String pageFile = null;
		String pageFileDir = null;
		
		try {
			//System.out.println("PAGE_LOCATION: " + BatchJobConstants.PAGE_LOCATION);
			
			pageFileDir = BatchJobConstants.PAGE_LOCATION;
			
			//pageFileDir = "d:/mybbs/pages";
			
			pageFile = pageFileDir + File.separator + sourcePage.getId() + BatchJobConstants.PAGE_FILE_SUFFIX;
			
			File file = new File(pageFile);
		
			FileUtils.writeByteArrayToFile(file, response);
		} catch (IOException e) {
			logger.error("Error occurs when generate page file:" + pageFile + ", " + e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error occurs when generate page file, " + e);
			e.printStackTrace();
		}
		
		return sourcePage;
	}

}

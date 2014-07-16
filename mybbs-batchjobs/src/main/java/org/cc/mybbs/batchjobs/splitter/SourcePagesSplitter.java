package org.cc.mybbs.batchjobs.splitter;

import java.util.List;

import org.apache.log4j.Logger;
import org.cc.mybbs.batchjobs.service.DownloadSourcePageService;
import org.mybbs.base.model.SourcePage;
import org.springframework.stereotype.Component;

@Component
public class SourcePagesSplitter {
	
	Logger logger = Logger.getLogger(SourcePagesSplitter.class);

	public List<SourcePage> splite(List<SourcePage> list){
		logger.info("Splite sourcePage list.");
		return list;
	}
}

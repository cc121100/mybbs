package org.cc.mybbs.batchjobs.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.cc.mybbs.batchjobs.utils.MockSessionPool;
import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateLoginUserService implements MessageHandler{
	
	Logger logger = Logger.getLogger(UpdateLoginUserService.class);

	@Override
	public void handleMessage(Message<?> msg)
			throws MessagingException {
		//System.err.println("cur date time: " + new Date().getTime());
		Map<String,String> map = (Map<String, String>) msg.getPayload();
		System.err.println(new Date() + ", UpdateLoginUserService, received msg: " +map.get("tcd") + " - " +map.get("visitedTime"));
		String tcd = map.get("tcd");
		String visitedTimeStr = map.get("visitedTime");
		Map m = MockSessionPool.getSession_map();
		
		if(StringUtils.isNotEmpty(tcd) && StringUtils.isNotEmpty(visitedTimeStr)){
			Long visitedTime = null;
			try{
				visitedTime = Long.parseLong(visitedTimeStr);
				
				// add in to session map
				MockSessionPool.add(tcd, visitedTime);
				
			}catch(Exception e){
				logger.error("Error occurs when UpdateLoginUserService, " + e);
			}
		}
		
		
	}
}

package org.cc.mybbs.batchjobs.service;


import java.util.Date;
import java.util.List;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateLoginUserService implements MessageHandler{

	@Override
	public void handleMessage(Message<?> msg)
			throws MessagingException {
		
		System.err.println(new Date() + ", UpdateLoginUserService, received msg: " +msg.getPayload());
	}
}

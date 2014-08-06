package org.cc.mybbs.batchjobs;

import org.cc.mybbs.batchjobs.service.SPSampleService;
import org.cc.mybbs.batchjobs.utils.SPSamplePool;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-integration.xml");
    	
    	// init spsList
    	SPSampleService sp = (SPSampleService) ac.getBean(SPSampleService.class);
    	SPSamplePool.setSpsList(sp.iniSPSampleList());
    	
    	
    	MessageChannel controlChannel = ac.getBean("sourcePagesInfoChl", MessageChannel.class);
    	
    	//controlChannel.send(new GenericMessage<String>("@loadActiveSPFromDB.start()"));
    	//controlChannel.send(new GenericMessage<String>("@loadSubscribedSPFromDB.start()"));
    	//controlChannel.send(new GenericMessage<String>("@loadLoginedAndDefaultSPFromDB.start()"));
    }
}

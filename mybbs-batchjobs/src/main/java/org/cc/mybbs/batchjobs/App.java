package org.cc.mybbs.batchjobs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	new ClassPathXmlApplicationContext("classpath:spring/applicationContext-integration.xml");
    }
}

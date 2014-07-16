package org.cc.mybbs.batchjobs.constants;

import org.mybbs.base.utils.PropertyUtil;

public class BatchJobConstants {
	
	
	public static String PAGE_LOCATION = null;
	public static String URL_LOCATION = null;
	public static String PAGE_FILE_SUFFIX = null;
	public static String URL_FILE_SUFFIX = null;
	
	static{
		PropertyUtil.loadProperties("batchjobs.properties");
		
		PAGE_LOCATION = PropertyUtil.getValue("PAGE_LOCATION");
		URL_LOCATION = PropertyUtil.getValue("URL_LOCATION");
		PAGE_FILE_SUFFIX = PropertyUtil.getValue("PAGE_FILE_SUFFIX");
		URL_FILE_SUFFIX = PropertyUtil.getValue("URL_FILE_SUFFIX");
	}
	
	//public static String REGEX_HREF = "<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]";
	
	public static String REGEX_HREF = "<a\\s.*?href\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"?[^>]*>(.*?)</a>";

}

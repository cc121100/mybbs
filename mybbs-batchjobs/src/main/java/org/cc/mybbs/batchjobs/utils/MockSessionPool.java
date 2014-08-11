package org.cc.mybbs.batchjobs.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MockSessionPool {

	private static Map<String, Long> SESSION_MAP = new LinkedHashMap<String, Long>();
	
	private static Long TIME_OUT = new Long(1000 * 60 * 10);

	public static synchronized Map<String, Long> getSession_map() {
		return SESSION_MAP;
	}
	
	public static synchronized void add(String key, Long value){
		SESSION_MAP.put(key, value);
	}
	
	public static synchronized Set<String> removeOverTimeSessions(long curTime){
		Iterator<Entry<String, Long>> it = SESSION_MAP.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Long> entry = it.next();
			if(curTime - entry.getValue() > TIME_OUT){
				it.remove();
			}
		}
		return SESSION_MAP.keySet();
	}
	
	
}

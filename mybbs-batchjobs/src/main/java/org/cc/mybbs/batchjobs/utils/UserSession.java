package org.cc.mybbs.batchjobs.utils;

import java.util.LinkedList;
import java.util.List;

public class UserSession {

	private static List<String> userSessionIds = new LinkedList<String>();

	public static void add(String userId){
		synchronized (userSessionIds) {
			userSessionIds.add(userId);
		}
	}
	
	public static void remove(String userId){
		synchronized (userSessionIds) {
			userSessionIds.remove(userId);
		}
	}
	
	public static List<String> getUserSessionId() {
		return userSessionIds;
	}
	

	
}

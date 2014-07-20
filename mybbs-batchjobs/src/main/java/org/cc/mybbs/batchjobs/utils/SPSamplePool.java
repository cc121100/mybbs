package org.cc.mybbs.batchjobs.utils;

import java.util.ArrayList;
import java.util.List;

import org.mybbs.base.model.SourcePageSample;

public class SPSamplePool {

	private static List<SourcePageSample> spsList = new ArrayList<SourcePageSample>();

	public static List<SourcePageSample> getSpsList() {
		return spsList;
	}

	public static void setSpsList(List<SourcePageSample> spsList) {
		SPSamplePool.spsList = spsList;
	}
	
}

package org.cc.mybbs.batchjobs.utils;

import java.util.List;

import org.mybbs.base.model.SourcePageSample;

public class LazyLoadUtils {

	public static void lazyLoadSPFilters(List<SourcePageSample> spsList){
		for(SourcePageSample sps : spsList){
			sps.getSourcePageFilters().size();
		}
	}
}

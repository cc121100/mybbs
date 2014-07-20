package org.cc.mybbs.batchjobs.service;

import java.util.List;

import org.cc.mybbs.batchjobs.utils.LazyLoadUtils;
import org.mybbs.base.dao.SourcePageSampleDAO;
import org.mybbs.base.model.SourcePageSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SPSampleService {
	
	@Autowired
	SourcePageSampleDAO sourcePageSampleDAO;
	

	@Transactional
	public List<SourcePageSample> iniSPSampleList(){
		List<SourcePageSample> spsList = sourcePageSampleDAO.findAll();
		LazyLoadUtils.lazyLoadSPFilters(spsList);
		return spsList;
	}
}

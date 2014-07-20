package org.cc.mybbs.batchjobs.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.cc.mybbs.batchjobs.constants.BatchJobConstants;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.model.Filter;
import org.mybbs.base.model.SourcePage;
import org.mybbs.base.model.SourcePageFilter;
import org.mybbs.base.model.SourcePageFilterDetail;
import org.mybbs.base.model.SourcePageSample;
import org.springframework.stereotype.Component;

@Component
public class FilterSourcePageService {

	private static Logger logger = Logger.getLogger(FilterSourcePageService.class);

	public SourcePage filterSourcePage(SourcePage sourcePage) {

		logger.info("Start filter Source page");

		// 1. load page from html page location
		String pageFilePath = BatchJobConstants.PAGE_LOCATION + File.separator + sourcePage.getId() + ".page";

		String htmlStr = loadPageFileAsString(pageFilePath);

		// 2. get fit urls
		Map<String,String> map = parseHtml(htmlStr, sourcePage.getSpSample(), sourcePage.getTargetPageName());
		
		sourcePage.setUrlAndContentMap(map);
		
		logger.info("End filter Source page with source page count:" + map.keySet().size());
		
		if(map.keySet().size() > 0){
			return sourcePage;
		}else{
			return null;
		}

	}

	public String loadPageFileAsString(String pageFilePath) {
		String str = null;

		File file = new File(pageFilePath);
		if (!file.exists()) {
			logger.error("Page file does not exsit with pageFilePath:" + pageFilePath);
			return null;
		}
		try {
			str = FileUtils.readFileToString(file, BaseConstants.ENCODING_UTF8);
		} catch (IOException e) {
			logger.error("Error occurs when loadPageFileAsString, " + e);
			e.printStackTrace();
			return null;
		}
		logger.info("Success Loading html string from file: " + pageFilePath);
		return str;
	}

	/***
	 * parse one time for each sourcePageFilter,
	 * 
	 * @param htmlStr
	 * @param sourcePage
	 * @return key: href value: xxx, <a href=''>xxx</a>
	 */
	public Map<String,String> parseHtml(String htmlStr, SourcePageSample sps, String targetPageName) {
		
		logger.info("Parse html for source page: "+ targetPageName);
		
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		List<SourcePageFilter> sourcePageFilters = sps.getSourcePageFilters();
		
		if (sourcePageFilters == null || sourcePageFilters.size() < 1) {
			logger.error("Error occurs when parse html, sourcePageFilter list is null or empty for source page: "+ targetPageName);
			return null;
		}

		// sort sourcePageFilters by seqNum asc
		Collections.sort(sourcePageFilters, new Comparator<SourcePageFilter>() {
			@Override
			public int compare(SourcePageFilter f1, SourcePageFilter f2) {
				return f1.getSeqNum().compareTo(f2.getSeqNum());
			}
		});

		String parsedHtmlStr = null;
		Parser parser = new Parser();
		NodeFilter nodeFilter = null;
		NodeList nodeList = null;

		try {
			parser.setEncoding(BaseConstants.ENCODING_UTF8);

			// 1. loop sourcePage.sourcePageFilters, parse each one
			for (SourcePageFilter spFilter : sourcePageFilters) {
				logger.info("Start parse html for sourcePageFilter [" + spFilter.getSourcePageFilterName() + ", seqNum = " + spFilter.getSeqNum() + "]");

				if (spFilter.getSeqNum() == 1) {
					parser.setResource(htmlStr);
				} else {
					parser.setResource(parsedHtmlStr);
				}

				nodeFilter = generateNodeFilterForSourcePageFilter(spFilter);
				nodeList = parser.parse(nodeFilter);
				parsedHtmlStr = nodeList.toHtml();
				//System.out.println("==========================================="+ spFilter.getSourcePageFilterName());
				logger.info("End parse html for sourcePageFilter [" + spFilter.getSourcePageFilterName() + ", seqNum = " + spFilter.getSeqNum() + "]");
			}
			
			// 2. get the last nodeList and ,get all the href and content of <a href=''>...</a>
			
			// 3. store the href and content in map as <href,content>


			for (NodeIterator i = nodeList.elements(); i.hasMoreNodes();) {
				Node node = i.nextNode();
				/*System.out.println(node.toPlainTextString());
				System.out.println(node.toHtml());*/
				Pattern p = Pattern.compile(BatchJobConstants.REGEX_HREF,Pattern.CASE_INSENSITIVE);
				//System.out.println(node.toHtml());
				Matcher m = p.matcher(node.toHtml());
				while (m.find()) {
					String link = m.group(1).trim();
					resultMap.put(link, node.toPlainTextString().trim());
				}
			}

		} catch (Exception e2) {
			logger.error("Error occurs when parse html string , " + e2);
			e2.printStackTrace();
		}

		return resultMap;
	}
	
	
	public NodeFilter generateNodeFilterForSourcePageFilter(SourcePageFilter spFilter) throws Exception{
		
		Map<SourcePageFilterDetail, NodeFilter> resultMap= new LinkedHashMap<SourcePageFilterDetail, NodeFilter>();
		List<SourcePageFilterDetail> sourcePageFilterDetails = spFilter.getSourcePageFilterDetails();
		
		if (sourcePageFilterDetails == null || sourcePageFilterDetails.size() < 1) {
			logger.error("Error occurs when parse html, sourcePageFilterDetail list is null or empty for sourcePageFilter: " + spFilter.getSourcePageFilterName());
			return null;
		}

		// sort sourcePageFilterDetails by subNum desc
		Collections.sort(sourcePageFilterDetails,new Comparator<SourcePageFilterDetail>() {
			@Override
			public int compare(SourcePageFilterDetail o1,
					SourcePageFilterDetail o2) {
				return o2.getSubNum().compareTo(o1.getSubNum());
			}
		});

		for (SourcePageFilterDetail spfDetail : sourcePageFilterDetails) {
			Filter filter = spfDetail.getFilter();
			//logger.info("Start generate filter [" + filter.getFilterName() + ", subNum=" + spfDetail.getSubNum() + "] for sourcePageFilter [" + spFilter.getSourcePageFilterName() + "]");

			Class<NodeFilter> filterClass = (Class<NodeFilter>) Class.forName(filter.getFilterClassName());

			if (BaseConstants.FILTER_REFLECT_METHOD_CONST.equals(filter.getSetParamMethodName())) {
				String[] classParams = filter.getFilterClassParams().split(",");
				if (classParams == null || classParams.length < 1) {
					logger.error("Error occurs when get class params, null or empty");
					return null;
				}

				Class[] classParamsClass = new Class[classParams.length];
				for (int i = 0; i < classParams.length; i++) {
					classParamsClass[i] = Class.forName(classParams[i]);
				}

				Constructor<NodeFilter> cons = filterClass.getConstructor(classParamsClass);
				NodeFilter node = null;

				if (StringUtils.isNotEmpty(spfDetail.getParamValue1()) && StringUtils.isNotEmpty(spfDetail.getParamValue2())) {
					node = cons.newInstance(spfDetail.getParamValue1(),spfDetail.getParamValue2());
				} else if (StringUtils.isNotEmpty(spfDetail.getParamValue1())) {
					node = cons.newInstance(spfDetail.getParamValue1());
				} else {

					List<NodeFilter> childNodelist = new ArrayList<NodeFilter>();
					for (Entry<SourcePageFilterDetail, NodeFilter> entry : resultMap.entrySet()) {
						if (spfDetail.getId().equals(entry.getKey().getParentNode().getId())) {
							childNodelist.add(entry.getValue());
						}
					}

					if (childNodelist.size() < 1) {
						logger.error("Error orrurs when get child node for parent node");
						return null;
					} else if (childNodelist.size() == 1) {
						node = cons.newInstance(childNodelist.get(0));
					} else {
						NodeFilter[] nodeFilterArr = new NodeFilter[childNodelist.size()];
						Object[] objs = { childNodelist.toArray(nodeFilterArr) };
						node = cons.newInstance(objs);
					}
				}
				
				resultMap.put(spfDetail, node);

			} else {//TODO use SET method to generate

			}
			//logger.info("End generate filter [" + filter.getFilterName() + ", subNum=" + spfDetail.getSubNum() + "] for sourcePageFilter [" + spFilter.getSourcePageFilterName() + "]");
		}
		
		NodeFilter nodeFilter = null;
		for (Entry<SourcePageFilterDetail, NodeFilter> entry : resultMap.entrySet()) {
			if (entry.getKey().getSubNum() == 1) {
				nodeFilter = entry.getValue();
				break;
			}
		}
		
		return nodeFilter;
		
	}
	
	

}

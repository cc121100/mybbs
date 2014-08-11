package org.mybbs.base.init;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.NodeFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybbs.base.constants.BaseConstants;
import org.mybbs.base.dao.FilterDAO;
import org.mybbs.base.dao.SourcePageDAO;
import org.mybbs.base.dao.SourcePageSampleDAO;
import org.mybbs.base.dao.UserSettingDAO;
import org.mybbs.base.model.Filter;
import org.mybbs.base.model.SourcePage;
import org.mybbs.base.model.SourcePageFilter;
import org.mybbs.base.model.SourcePageFilterDetail;
import org.mybbs.base.model.SourcePageSample;
import org.mybbs.base.model.UserSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-base.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class DataInit {
	
	/*@PersistenceContext
	private EntityManager em;*/
	
	@Autowired
	private FilterDAO filterDAO;
	
	@Autowired
	private SourcePageDAO sourcePageDAO;
	
	@Autowired
	private UserSettingDAO userSettingDAO;
	
	@Autowired
	private SourcePageSampleDAO sourcePageSampleDAO;
	

	@Test
	public void initFilterData(){
		List<Filter> filterList = new ArrayList<Filter>();
		
		Filter andFilter = new Filter();
		andFilter.setFilterName("AndFilter");
		andFilter.setFilterClassName("org.htmlparser.filters.AndFilter");
		andFilter.setFilterClassParams("[Lorg.htmlparser.NodeFilter;");
		andFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		andFilter.setSetParamMethodName("Construct");
		filterList.add(andFilter);
		
		Filter cssSelectorNodeFilter = new Filter();
		cssSelectorNodeFilter.setFilterName("CssSelectorNodeFilter");
		cssSelectorNodeFilter.setFilterClassName("org.htmlparser.filters.CssSelectorNodeFilter");
		cssSelectorNodeFilter.setFilterClassParams("java.lang.String");
		cssSelectorNodeFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		cssSelectorNodeFilter.setSetParamMethodName("Construct");
		filterList.add(cssSelectorNodeFilter);
		
		Filter hasAttributeFilter = new Filter();
		hasAttributeFilter.setFilterName("HasAttributeFilter");
		hasAttributeFilter.setFilterClassName("org.htmlparser.filters.HasAttributeFilter");
		hasAttributeFilter.setFilterClassParams("java.lang.String,java.lang.String");
		hasAttributeFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		hasAttributeFilter.setSetParamMethodName("Construct");
		filterList.add(hasAttributeFilter);
		
		Filter hasChildFilter = new Filter();
		hasChildFilter.setFilterName("HasChildFilter");
		hasChildFilter.setFilterClassName("org.htmlparser.filters.HasChildFilter");
		hasChildFilter.setFilterClassParams("org.htmlparser.NodeFilter");
		hasChildFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_NODE);
		hasChildFilter.setSetParamMethodName("Construct");
		filterList.add(hasChildFilter);
		
		Filter hasParentFilter = new Filter();
		hasParentFilter.setFilterName("HasParentFilter");
		hasParentFilter.setFilterClassName("org.htmlparser.filters.HasParentFilter");
		hasParentFilter.setFilterClassParams("org.htmlparser.NodeFilter");
		hasParentFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_NODE);
		hasParentFilter.setSetParamMethodName("Construct");
		filterList.add(hasParentFilter);
		
		Filter hasSiblingFilter = new Filter();
		hasSiblingFilter.setFilterName("HasSiblingFilter");
		hasSiblingFilter.setFilterClassName("org.htmlparser.filters.HasSiblingFilter");
		hasSiblingFilter.setFilterClassParams("org.htmlparser.NodeFilter");
		hasSiblingFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_NODE);
		hasSiblingFilter.setSetParamMethodName("Construct");
		filterList.add(hasSiblingFilter);
		
		Filter isEqualFilter = new Filter();
		isEqualFilter.setFilterName("IsEqualFilter");
		isEqualFilter.setFilterClassName("org.htmlparser.filters.IsEqualFilter");
		isEqualFilter.setFilterClassParams("org.htmlparser.NodeFilter");
		isEqualFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_NODE);
		isEqualFilter.setSetParamMethodName("Construct");
		filterList.add(isEqualFilter);
		
		Filter linkRegexFilter = new Filter();
		linkRegexFilter.setFilterName("LinkRegexFilter");
		linkRegexFilter.setFilterClassName("org.htmlparser.filters.LinkRegexFilter");
		linkRegexFilter.setFilterClassParams("java.lang.String");
		linkRegexFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		linkRegexFilter.setSetParamMethodName("Construct");
		filterList.add(linkRegexFilter);
		
		Filter linkStringFilter = new Filter();
		linkStringFilter.setFilterName("LinkStringFilter");
		linkStringFilter.setFilterClassName("org.htmlparser.filters.LinkStringFilter");
		linkStringFilter.setFilterClassParams("java.lang.String");
		linkStringFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		linkStringFilter.setSetParamMethodName("Construct");
		filterList.add(linkStringFilter);
		
		Filter nodeClassFilter = new Filter();
		nodeClassFilter.setFilterName("NodeClassFilter");
		nodeClassFilter.setFilterClassName("org.htmlparser.filters.NodeClassFilter");
		nodeClassFilter.setFilterClassParams("java.lang.Class");
		nodeClassFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_CLASS);
		nodeClassFilter.setSetParamMethodName("Construct");
		filterList.add(nodeClassFilter);
		
		Filter notFilter = new Filter();
		notFilter.setFilterName("NotFilter");
		notFilter.setFilterClassName("org.htmlparser.filters.NotFilter");
		notFilter.setFilterClassParams("org.htmlparser.NodeFilter");
		notFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_NODE);
		notFilter.setSetParamMethodName("Construct");
		filterList.add(notFilter);
		
		Filter orFilter = new Filter();
		orFilter.setFilterName("OrFilter");
		orFilter.setFilterClassName("org.htmlparser.filters.OrFilter");
		orFilter.setFilterClassParams("[Lorg.htmlparser.NodeFilter;");
		orFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_NODE);
		orFilter.setSetParamMethodName("Construct");
		filterList.add(orFilter);
		
		Filter regexFilter = new Filter();
		regexFilter.setFilterName("RegexFilter");
		regexFilter.setFilterClassName("org.htmlparser.filters.RegexFilter");
		regexFilter.setFilterClassParams("java.lang.String");
		regexFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		regexFilter.setSetParamMethodName("Construct");
		filterList.add(regexFilter);
		
		Filter stringFilter = new Filter();
		stringFilter.setFilterName("StringFilter");
		stringFilter.setFilterClassName("org.htmlparser.filters.StringFilter");
		stringFilter.setFilterClassParams("java.lang.String");
		stringFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		stringFilter.setSetParamMethodName("Construct");
		filterList.add(stringFilter);
		
		Filter tagNameFilter = new Filter();
		tagNameFilter.setFilterName("TagNameFilter");
		tagNameFilter.setFilterClassName("org.htmlparser.filters.TagNameFilter");
		tagNameFilter.setFilterClassParams("java.lang.String");
		tagNameFilter.setParamType(BaseConstants.FILTER_PARAM_TYPE_STRING);
		tagNameFilter.setSetParamMethodName("Construct");
		filterList.add(tagNameFilter);
		
		for(Filter filter : filterList){
			filterDAO.saveAndFlush(filter);
		}
		
	}
	
	@Test
	public void getFiltersTest(){
		/*List<Filter> list = filterDAO.findAll();
		for(Filter filter : list){
			System.out.println(filter.getFilterName());
		}*/
		
		/*List<SourcePage> sourcePageList =  sourcePageDAO.getSourcePagesByStatusAndCategory("A", BaseConstants.SP_CATEGORY_T);
		for(SourcePage sp :sourcePageList){
			
			System.out.println(sp.getTargetPageName() + ":" + sp.getSourcePageFilters().size());
			for(SourcePageFilter spFilter : sp.getSourcePageFilters()){
				System.out.println("  " + spFilter.getSourcePageFilterName());
				for(SourcePageFilterDetail spfd : spFilter.getSourcePageFilterDetails()){
					System.out.println("    " + spfd.getId());
				}
			}
		}*/
	}

	@Test
	public void initSourcePageAndSourcePageFilterData(){
		
		List<Filter> list = filterDAO.findAll();
		for(Filter filter : list){
			System.out.println(filter.getFilterName());
		}
		
		//sourcePage
		
		/*SourcePage sourcePage = new SourcePage();
		sourcePage.setTargetPageName("天涯论坛首页热帖Sample");
		sourcePage.setTargetPageUrl("http://bbs.tianya.cn");
		sourcePage.setStatus("A");
		sourcePage.setDomainName("http://bbs.tianya.cn");
		sourcePage.setCategory(BaseConstants.SP_CATEGORY_S);*/
		
		SourcePageSample spSampleTianya = new SourcePageSample();
		spSampleTianya.setSampleName("天涯论坛首页热帖Sample");
		
		
		List<SourcePageFilter> sourcePageFilters = new ArrayList<SourcePageFilter>();
		
		//new HasAttributeFilter("_tystat", "热帖榜");
		SourcePageFilter pageFilter1 = new SourcePageFilter();
		pageFilter1.setSourcePageFilterName("_tystat='热帖榜'");
		/*List<SourcePage> spList = new ArrayList<SourcePage>();
		spList.add(sourcePage);
		pageFilter1.setSourcePages(spList);*/
		pageFilter1.setSourcePageSample(spSampleTianya);
		pageFilter1.setSeqNum(1);
		
		List<SourcePageFilterDetail> detailList1 = new ArrayList<SourcePageFilterDetail>();
		
		SourcePageFilterDetail detail1 = new SourcePageFilterDetail();
		detail1.setSourcePageFilter(pageFilter1);
		detail1.setFilter(list.get(2));
		detail1.setParamValue1("_tystat");
		detail1.setParamValue2("热帖榜");
		detail1.setSubNum(1);
		
		detailList1.add(detail1);
		pageFilter1.setSourcePageFilterDetails(detailList1);
		
		sourcePageFilters.add(pageFilter1);
		
		//new TagNameFilter("ul");
		SourcePageFilter pageFilter2 = new SourcePageFilter();
		pageFilter2.setSourcePageFilterName("ul");
		
//		pageFilter2.setSourcePages(spList);
		pageFilter2.setSourcePageSample(spSampleTianya);
		pageFilter2.setSeqNum(2);
		
		List<SourcePageFilterDetail> detailList2 = new ArrayList<SourcePageFilterDetail>();
		
		SourcePageFilterDetail detail2 = new SourcePageFilterDetail();
		detail2.setSourcePageFilter(pageFilter2);
		detail2.setFilter(list.get(14));
		detail2.setParamValue1("ul");
		//detail2.setParamValue2("热帖榜");
		detail2.setSubNum(1);
		
		detailList2.add(detail2);
		pageFilter2.setSourcePageFilterDetails(detailList2);
		
		sourcePageFilters.add(pageFilter2);
		
		//new TagNameFilter("li");
		SourcePageFilter pageFilter3 = new SourcePageFilter();
		pageFilter3.setSourcePageFilterName("li");
//		pageFilter3.setSourcePages(spList);
		pageFilter3.setSourcePageSample(spSampleTianya);
		pageFilter3.setSeqNum(3);
		
		List<SourcePageFilterDetail> detailList3 = new ArrayList<SourcePageFilterDetail>();
		
		SourcePageFilterDetail detail3 = new SourcePageFilterDetail();
		detail3.setSourcePageFilter(pageFilter3);
		detail3.setFilter(list.get(14));
		detail3.setParamValue1("li");
		//detail2.setParamValue2("热帖榜");
		detail3.setSubNum(1);
		
		detailList3.add(detail3);
		pageFilter3.setSourcePageFilterDetails(detailList3);
		
		sourcePageFilters.add(pageFilter3);
		
		//new AndFilter(new TagNameFilter("a"),new HasParentFilter(new TagNameFilter("div")));
		SourcePageFilter pageFilter4 = new SourcePageFilter();
		pageFilter4.setSourcePageFilterName("AndFilter");
//		pageFilter4.setSourcePages(spList);
		pageFilter4.setSourcePageSample(spSampleTianya);
		pageFilter4.setSeqNum(4);
		
		List<SourcePageFilterDetail> detailList4 = new ArrayList<SourcePageFilterDetail>();
		
		SourcePageFilterDetail detail4_5 = new SourcePageFilterDetail();
		detail4_5.setSourcePageFilter(pageFilter4);
		detail4_5.setFilter(list.get(0));
		//detail4_4.setParamValue1("a");
		//detail4_5.setRefNodeId(detail4_3.getId() + "," + detail4_4.getId());
		detail4_5.setSubNum(1);
		
		SourcePageFilterDetail detail4_4 = new SourcePageFilterDetail();
		detail4_4.setSourcePageFilter(pageFilter4);
		detail4_4.setFilter(list.get(14));
		detail4_4.setParamValue1("a");
		detail4_4.setParentNode(detail4_5);
		detail4_4.setSubNum(2);
		
		SourcePageFilterDetail detail4_3 = new SourcePageFilterDetail();
		detail4_3.setSourcePageFilter(pageFilter4);
		detail4_3.setFilter(list.get(4));
		detail4_3.setParentNode(detail4_5);
		detail4_3.setSubNum(2);
		
		SourcePageFilterDetail detail4_2 = new SourcePageFilterDetail();
		detail4_2.setSourcePageFilter(pageFilter4);
		detail4_2.setFilter(list.get(14));
		detail4_2.setParamValue1("div");
		detail4_2.setParentNode(detail4_3);
		detail4_2.setSubNum(3);
		
		
		detailList4.add(detail4_5);
		detailList4.add(detail4_4);
		detailList4.add(detail4_3);
		detailList4.add(detail4_2);
		pageFilter4.setSourcePageFilterDetails(detailList4);
		
		sourcePageFilters.add(pageFilter4);
		
		
		spSampleTianya.setSourcePageFilters(sourcePageFilters);
		sourcePageSampleDAO.saveAndFlush(spSampleTianya);
		
		SourcePage sourcePage2 = new SourcePage();
		sourcePage2.setTargetPageName("天涯论坛首页热帖");
		sourcePage2.setTargetPageUrl("http://bbs.tianya.cn");
		sourcePage2.setStatus("A");
		sourcePage2.setDomainName("http://bbs.tianya.cn");
		sourcePage2.setCategory(BaseConstants.SP_CATEGORY_T);
		sourcePage2.setSourcePageSampleId(spSampleTianya.getId());
		sourcePageDAO.saveAndFlush(sourcePage2);
		
		
		//jianshu.io
		/*SourcePage jianshu = new SourcePage();
		jianshu.setTargetPageName("简书首页Sample");
		jianshu.setTargetPageUrl("http://jianshu.io");
		jianshu.setStatus("A");
		jianshu.setDomainName("http://jianshu.io");
		jianshu.setCategory(BaseConstants.SP_CATEGORY_S);*/
		
		SourcePageSample spSampleJianShu = new SourcePageSample();
		spSampleJianShu.setSampleName("简书首页Sample");
		
		List<SourcePageFilter> jiaoshuFilters = new ArrayList<SourcePageFilter>();
		
		//new HasAttributeFilter("_tystat", "热帖榜");
		SourcePageFilter jianshuFilter1 = new SourcePageFilter();
		jianshuFilter1.setSourcePageFilterName("jianshu filter");
		//List<SourcePage> jianshuSps = new ArrayList<SourcePage>();
		//jianshuSps.add(jianshu);
//		jianshuFilter1.setSourcePages(jianshuSps);
		jianshuFilter1.setSourcePageSample(spSampleJianShu);
		jianshuFilter1.setSeqNum(1);
		
		List<SourcePageFilterDetail> jianshuDetailList1 = new ArrayList<SourcePageFilterDetail>();
		
		//andFilter
		SourcePageFilterDetail jianshuDetail1 = new SourcePageFilterDetail();
		jianshuDetail1.setSourcePageFilter(jianshuFilter1);
		jianshuDetail1.setFilter(list.get(0));
		jianshuDetail1.setSubNum(1);
		
		//TagNameFilter("a");
		SourcePageFilterDetail jianshuDetail2 = new SourcePageFilterDetail();
		jianshuDetail2.setSourcePageFilter(jianshuFilter1);
		jianshuDetail2.setFilter(list.get(14));
		jianshuDetail2.setParamValue1("a");
		jianshuDetail2.setParentNode(jianshuDetail1);
		jianshuDetail2.setSubNum(2);
		
		//HasAttributeFilter("class", "title");
		SourcePageFilterDetail jianshuDetail3 = new SourcePageFilterDetail();
		jianshuDetail3.setSourcePageFilter(jianshuFilter1);
		jianshuDetail3.setFilter(list.get(2));
		jianshuDetail3.setParamValue1("class");
		jianshuDetail3.setParamValue2("title");
		jianshuDetail3.setParentNode(jianshuDetail1);
		jianshuDetail3.setSubNum(2);
		
		//new HasParentFilter
		SourcePageFilterDetail jianshuDetail4 = new SourcePageFilterDetail();
		jianshuDetail4.setSourcePageFilter(jianshuFilter1);
		jianshuDetail4.setFilter(list.get(4));
		jianshuDetail4.setParentNode(jianshuDetail1);
		jianshuDetail4.setSubNum(2);
		
		//new HasAttributeFilter("class","article")
		SourcePageFilterDetail jianshuDetail5 = new SourcePageFilterDetail();
		jianshuDetail5.setSourcePageFilter(jianshuFilter1);
		jianshuDetail5.setFilter(list.get(2));
		jianshuDetail5.setParamValue1("class");
		jianshuDetail5.setParamValue2("article");
		jianshuDetail5.setParentNode(jianshuDetail4);
		jianshuDetail5.setSubNum(3);
		
		jianshuDetailList1.add(jianshuDetail1);
		jianshuDetailList1.add(jianshuDetail2);
		jianshuDetailList1.add(jianshuDetail3);
		jianshuDetailList1.add(jianshuDetail4);
		jianshuDetailList1.add(jianshuDetail5);
		
		jianshuFilter1.setSourcePageFilterDetails(jianshuDetailList1);
		
		
		
		jiaoshuFilters.add(jianshuFilter1);
		spSampleJianShu.setSourcePageFilters(jiaoshuFilters);
		
		sourcePageSampleDAO.saveAndFlush(spSampleJianShu);
		
		SourcePage jianshu2 = new SourcePage();
		jianshu2.setTargetPageName("简书首页");
		jianshu2.setTargetPageUrl("http://jianshu.io");
		jianshu2.setStatus("A");
		jianshu2.setDomainName("http://jianshu.io");
		jianshu2.setCategory(BaseConstants.SP_CATEGORY_T);
		jianshu2.setSourcePageSampleId(spSampleJianShu.getId());
		sourcePageDAO.saveAndFlush(jianshu2);
		
		
	}
	
	@Test
	public void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		/*List<UserSetting> usList = userSettingDAO.findAll();
		for(UserSetting us : usList){
			System.out.println(us.getSourcePages().size());
		}*/
		
		/*SourcePage sp = sourcePageDAO.findOne("4028e5e4473da73501473da740cb000c");
		List<SourcePage> spList = new ArrayList<SourcePage>();
		spList.add(sp);
		lazyLoadSPFilters(spList);*/
		
		List<SourcePage> spList = sourcePageDAO.getDefaultSP();
		for(SourcePage sp: spList){
			System.out.println(sp.getTargetPageName());
		}
		
	}
	
	private void lazyLoadSPFilters(List<SourcePageSample> spsList){
		for(SourcePageSample sps : spsList){
			sps.getSourcePageFilters().size();
		}
	}
	
	@Test
	public void test2(){
		/*List<String> list = new ArrayList<String>();
		list.add("bcb2a76e1df711e4a5b4e4115b5340f1");
		list.add("26875a1e1d3e11e49acfe4115b5340f1");*/
		String[] strs = {"bcb2a76e1df711e4a5b4e4115b5340f1","26875a1e1d3e11e49acfe4115b5340f1"};
		sourcePageDAO.getCurSp(strs);
	}
}

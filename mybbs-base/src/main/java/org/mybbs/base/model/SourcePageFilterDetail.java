package org.mybbs.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.mybbs.base.dao.SourcePageDAO;

@Entity
@Table(name="tbl_source_page_filter_detail")
public class SourcePageFilterDetail extends BaseModel{
	
	@Id
	@GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid",strategy="uuid") 
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "page_filter_id")
	private SourcePageFilter sourcePageFilter;
	
/*	private String pageId;
*/	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "filter_id")
	private Filter filter;
	
	/*private String filterId;
	*/
	
	@Column(name="param_value1")
	private String paramValue1;
	
	@Column(name="param_value2")
	private String paramValue2;
	
	@OneToOne()
	@JoinColumn(name = "parent_node_id")
	private SourcePageFilterDetail parentNode;
	
	@Column(name="sub_num" ,nullable=false)
	private Integer subNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SourcePageFilter getSourcePageFilter() {
		return sourcePageFilter;
	}

	public void setSourcePageFilter(SourcePageFilter sourcePageFilter) {
		this.sourcePageFilter = sourcePageFilter;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String getParamValue1() {
		return paramValue1;
	}

	public void setParamValue1(String paramValue1) {
		this.paramValue1 = paramValue1;
	}

	public String getParamValue2() {
		return paramValue2;
	}

	public void setParamValue2(String paramValue2) {
		this.paramValue2 = paramValue2;
	}

	public SourcePageFilterDetail getParentNode() {
		return parentNode;
	}

	public void setParentNode(SourcePageFilterDetail parentNode) {
		this.parentNode = parentNode;
	}

	public Integer getSubNum() {
		return subNum;
	}

	public void setSubNum(Integer subNum) {
		this.subNum = subNum;
	}

}

package org.mybbs.base.model;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_source_page")
public class SourcePage extends BaseModel {

	@Id
	@GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid",strategy="uuid") 
	private String id;
	
	@Column(name="target_page_name" ,nullable=false)
	@NotEmpty
	private String targetPageName;
	
	@Column(name="target_page_url" ,nullable=false)
	@NotEmpty
	private String targetPageUrl;
	
	@Column(name="category" ,nullable=false)
	private String category;
	
	@Column(name="sp_status", nullable = false)
	@NotEmpty
	private String status;
	
	@Column(name="domain_name" ,nullable=false)
	@NotEmpty
	private String domainName;
	
	@Column(name="md5_code")
	private String md5Code;
	
	/*@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name="tbl_sp_to_sp_filter", 
			joinColumns = @JoinColumn(name="source_page_id",referencedColumnName="id"), 
			inverseJoinColumns = @JoinColumn(name="source_pagefilter_id",referencedColumnName = "id"),
			uniqueConstraints = @UniqueConstraint(columnNames={"source_page_id" ,"source_pagefilter_id"})
	)*/
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="sourcePage")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SourcePageFilter> sourcePageFilters;
	
	@ManyToMany(mappedBy = "sourcePages")
	//@JoinColumn(name = "page_id")
	private List<UserSetting> userSettings;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sample_sp_id")
	private SourcePage sampleSourcePage;
	
	@Transient
	private Map<String, String> urlAndContentMap;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetPageName() {
		return targetPageName;
	}

	public void setTargetPageName(String targetPageName) {
		this.targetPageName = targetPageName;
	}

	public String getTargetPageUrl() {
		return targetPageUrl;
	}

	public void setTargetPageUrl(String targetPageUrl) {
		this.targetPageUrl = targetPageUrl;
	}

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public List<SourcePageFilter> getSourcePageFilters() {
		return sourcePageFilters;
	}

	public void setSourcePageFilters(List<SourcePageFilter> sourcePageFilters) {
		this.sourcePageFilters = sourcePageFilters;
	}

	public Map<String, String> getUrlAndContentMap() {
		return urlAndContentMap;
	}

	public void setUrlAndContentMap(Map<String, String> urlAndContentMap) {
		this.urlAndContentMap = urlAndContentMap;
	}

	public List<UserSetting> getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(List<UserSetting> userSettings) {
		this.userSettings = userSettings;
	}

	public SourcePage getSampleSourcePage() {
		return sampleSourcePage;
	}

	public void setSampleSourcePage(SourcePage sampleSourcePage) {
		this.sampleSourcePage = sampleSourcePage;
	}
	
	
}

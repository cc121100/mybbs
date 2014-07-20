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
	
	@Column(name="status", nullable = false)
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
	/*@OneToMany(cascade = CascadeType.PERSIST, mappedBy="sourcePage",fetch = FetchType.LAZY)
	//@LazyCollection(LazyCollectionOption.TRUE)
	private List<SourcePageFilter> sourcePageFilters;*/
	
	@ManyToMany(mappedBy = "sourcePages")
	//@JoinColumn(name = "page_id")
	private List<UserSetting> userSettings;
	
	//@OneToOne(fetch = FetchType.LAZY)
	@Column(name = "sp_sample_id")
	private String sourcePageSampleId;
	
	@Column(name="sample_sp_id_for_navi_sp")
	private String sampleSPIdForNaviSP;
	
	@Column(name = "sub_sp_domain_name")
	private String subSPDomainName;
	
	@Column(name = "unique_label", updatable = false)
	private String uniqueLabel;
	
	@Transient
	private SourcePageSample spSample;
	
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

	/*public List<SourcePageFilter> getSourcePageFilters() {
		return sourcePageFilters;
	}

	public void setSourcePageFilters(List<SourcePageFilter> sourcePageFilters) {
		this.sourcePageFilters = sourcePageFilters;
	}*/

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


	public SourcePageSample getSpSample() {
		return spSample;
	}

	public void setSpSample(SourcePageSample spSample) {
		this.spSample = spSample;
	}

	public String getSourcePageSampleId() {
		return sourcePageSampleId;
	}

	public void setSourcePageSampleId(String sourcePageSampleId) {
		this.sourcePageSampleId = sourcePageSampleId;
	}

	public String getSubSPDomainName() {
		return subSPDomainName;
	}
	
	public void setSubSPDomainName(String subSPDomainName) {
		this.subSPDomainName = subSPDomainName;
	}
	
	public String getUniqueLabel() {
		return uniqueLabel;
	}
	
	public void setUniqueLabel(String uniqueLabel) {
		this.uniqueLabel = uniqueLabel;
	}
	
	public String getSampleSPIdForNaviSP() {
		return sampleSPIdForNaviSP;
	}

	public void setSampleSPIdForNaviSP(String sampleSPIdForNaviSP) {
		this.sampleSPIdForNaviSP = sampleSPIdForNaviSP;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SourcePage other = (SourcePage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

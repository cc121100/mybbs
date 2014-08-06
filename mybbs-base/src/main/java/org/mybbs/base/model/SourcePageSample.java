package org.mybbs.base.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_source_page_sample")
public class SourcePageSample extends BaseModel{

	@Id
	@GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid",strategy="uuid") 
	private String id;
	
	@Column(name="sample_name" ,nullable=false)
	@NotEmpty
	private String sampleName;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="sourcePageSample",fetch = FetchType.LAZY)
	//@LazyCollection(LazyCollectionOption.TRUE)
	private List<SourcePageFilter> sourcePageFilters;
	
	@ManyToOne
	@JoinColumn(name = "sp_category_id")
	private SourcePageCategory sourcePageCategory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public List<SourcePageFilter> getSourcePageFilters() {
		return sourcePageFilters;
	}

	public void setSourcePageFilters(List<SourcePageFilter> sourcePageFilters) {
		this.sourcePageFilters = sourcePageFilters;
	}

	public SourcePageCategory getSourcePageCategory() {
		return sourcePageCategory;
	}

	public void setSourcePageCategory(SourcePageCategory sourcePageCategory) {
		this.sourcePageCategory = sourcePageCategory;
	}
	
	
}

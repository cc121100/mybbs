package org.mybbs.base.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_source_page_filter")
public class SourcePageFilter extends BaseModel {
	
	@Id
	@GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid",strategy="uuid") 
	private String id;
	
	/*@ManyToMany(mappedBy = "sourcePageFilters")
	//@JoinColumn(name = "page_id")
	private List<SourcePage> sourcePages;*/
	
	@ManyToOne
	@JoinColumn(name = "source_page_id")
	private SourcePage sourcePage;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="sourcePageFilter")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SourcePageFilterDetail> sourcePageFilterDetails;
	
	@Column(name="source_page_filter_name", nullable = false)
	@NotEmpty
	private String sourcePageFilterName;
	
	@Column(name="seq_num",nullable = false)
	private Integer seqNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public SourcePage getSourcePage() {
		return sourcePage;
	}

	public void setSourcePage(SourcePage sourcePage) {
		this.sourcePage = sourcePage;
	}

	public List<SourcePageFilterDetail> getSourcePageFilterDetails() {
		return sourcePageFilterDetails;
	}

	public void setSourcePageFilterDetails(
			List<SourcePageFilterDetail> sourcePageFilterDetails) {
		this.sourcePageFilterDetails = sourcePageFilterDetails;
	}

	public String getSourcePageFilterName() {
		return sourcePageFilterName;
	}

	public void setSourcePageFilterName(String sourcePageFilterName) {
		this.sourcePageFilterName = sourcePageFilterName;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}
	
	

}

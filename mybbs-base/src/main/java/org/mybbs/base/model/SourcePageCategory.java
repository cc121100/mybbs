package org.mybbs.base.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_source_page_category")
public class SourcePageCategory extends BaseModel{

	@Id
	@GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid",strategy="uuid") 
	private String id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="sourcePageCategory",fetch = FetchType.LAZY)
	private List<SourcePageSample> sourcePageSamples;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SourcePageSample> getSourcePageSamples() {
		return sourcePageSamples;
	}

	public void setSourcePageSamples(List<SourcePageSample> sourcePageSamples) {
		this.sourcePageSamples = sourcePageSamples;
	}
	
	
}

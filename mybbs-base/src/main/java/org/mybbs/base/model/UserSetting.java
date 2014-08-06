package org.mybbs.base.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_user_setting")
public class UserSetting extends BaseModel {
	
	@Id
	@GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid",strategy="uuid") 
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="category" )
	private String category;
	
	@Column(name="temp_cookie_id")
	private String tempCookieId;
	
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name="tbl_setting_to_source_page", 
			joinColumns = @JoinColumn(name="user_setting_id",referencedColumnName="id"), 
			inverseJoinColumns = @JoinColumn(name="source_page_id",referencedColumnName="id"),
			uniqueConstraints = @UniqueConstraint(columnNames={"user_setting_id" ,"source_page_id"})
	)
	List<SourcePage> sourcePages;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SourcePage> getSourcePages() {
		return sourcePages;
	}

	public void setSourcePages(List<SourcePage> sourcePages) {
		this.sourcePages = sourcePages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTempCookieId() {
		return tempCookieId;
	}

	public void setTempCookieId(String tempCookieId) {
		this.tempCookieId = tempCookieId;
	}

	

}

package com.gsore.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RamSize implements Comparable<RamSize> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="article_id")
	private Product article;	
	private String value;
		
	public RamSize() {}
	
	public RamSize(String value, Product article) {
		this.value = value;
		this.article = article;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Product getArticle() {
		return article;
	}
	public void setArticle(Product article) {
		this.article = article;
	}

	@Override
	public int compareTo(RamSize s) {
		return this.value.compareTo(s.getValue());		
	}
	
	
	
}

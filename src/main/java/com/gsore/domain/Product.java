package com.gsore.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int stock;	
	private double price;
	private String picture;
	
	@OneToMany(mappedBy="article", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RamSize> sizes;
	
	@OneToMany(mappedBy="article", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<StorageSize> brands;
	
	@OneToMany(mappedBy="article", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MobileColor> categories;

	public Product() {
	}
	
	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}
	
	public void decreaseStock(int amount) {
		this.stock -= amount;
	}
	

	public void addSize(RamSize size) {
        sizes.add(size);
        size.setArticle(this);
    }
 
    public void removeSize(RamSize size) {
        sizes.remove(size);
        size.setArticle(null);
    }
    
	public void addCategory(MobileColor category) {
        categories.add(category);
        category.setArticle(this);
    }
 
    public void removeCategory(MobileColor category) {
    	categories.remove(category);
        category.setArticle(null);
    }
    
	public void addSize(StorageSize brand) {
        brands.add(brand);
        brand.setArticle(this);
    }
 
    public void removeSize(StorageSize brand) {
    	brands.remove(brand);
        brand.setArticle(null);
    }
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Set<RamSize> getSizes() {
		return sizes;
	}
	public void setSizes(Set<RamSize> sizes) {
		this.sizes = sizes;
	}
	public Set<StorageSize> getBrands() {
		return brands;
	}
	public void setBrands(Set<StorageSize> brands) {
		this.brands = brands;
	}
	public Set<MobileColor> getCategories() {
		return categories;
	}
	public void setCategories(Set<MobileColor> categories) {
		this.categories = categories;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}

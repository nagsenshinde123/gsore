package com.gsore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gsore.domain.Product;

public interface ProductService {

	List<Product> findAllArticles();
	
	Page<Product> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands, String search);
		
	List<Product> findFirstArticles();

	Product findArticleById(Long id);
	
	Product saveArticle(Product article);

	void deleteArticleById(Long id);
	
	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

}

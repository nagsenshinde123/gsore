package com.gsore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsore.domain.Product;
import com.gsore.repository.ProductRepository;
import com.gsore.repository.ProductSpecification;
import com.gsore.service.ProductService;

@Service
@Transactional
public class ArticleServiceImpl implements ProductService {

	@Autowired
	private ProductRepository articleRepository;
	
	@Value("${articleservice.featured-items-number}")
	private int featuredArticlesNumber;

	@Override
	public List<Product> findAllArticles() {
		return (List<Product>) articleRepository.findAllEagerBy();
	}
	
	@Override
	public Page<Product> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, 
										List<String> sizes, List<String> categories, List<String> brands, String search) {		
		Page<Product> page = articleRepository.findAll(ProductSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands, search), pageable);
        return page;		
	}	
	
	@Override
	public List<Product> findFirstArticles() {
		return articleRepository.findAll(PageRequest.of(0,featuredArticlesNumber)).getContent(); 
	}

	@Override
	public Product findArticleById(Long id) {
		Optional<Product> opt = articleRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public Product saveArticle(Product article) {
		return articleRepository.save(article);
	}
	
	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public void deleteArticleById(Long id) {
		articleRepository.deleteById(id);		
	}

	
	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return articleRepository.findAllSizes();
	}

	@Override
	@Cacheable("categories")
	public List<String> getAllCategories() {
		return articleRepository.findAllCategories();
	}

	@Override
	@Cacheable("brands")
	public List<String> getAllBrands() {
		return articleRepository.findAllBrands();
	}
}

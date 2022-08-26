package com.gsore.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.gsore.domain.Product;
import com.gsore.domain.StorageSize;
import com.gsore.domain.MobileColor;
import com.gsore.domain.RamSize;

public class ProductSpecification {
	
	private ProductSpecification() {}
	
	@SuppressWarnings("serial")
	public static Specification<Product> filterBy(Integer priceLow, Integer priceHigh, List<String> sizes, 
												  List<String> categories, List<String> brands, String search) {			
		return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();                
                query.distinct(true);                
                if (sizes!=null && !sizes.isEmpty()) {
                	Join<Product, RamSize> joinSize = root.join("sizes");
                	predicates.add(criteriaBuilder.and(joinSize.get("value").in(sizes)));
                }
                if (categories!=null && !categories.isEmpty()) {
                	Join<Product, MobileColor> joinSize = root.join("categories");
                	predicates.add(criteriaBuilder.and(joinSize.get("name").in(categories)));
                }   
                if (brands!=null && !brands.isEmpty()) {
                	Join<Product, StorageSize> joinSize = root.join("brands");
                	predicates.add(criteriaBuilder.and(joinSize.get("name").in(brands)));
                }  
                
                if(search!=null && !search.isEmpty()) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%"+search+"%")));
                }
                if (priceLow!=null && priceLow >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow)));
                }
                if (priceHigh!=null && priceHigh >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };		
	}
}

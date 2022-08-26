package com.gsore.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gsore.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	List<Product> findAllEagerBy();	
		
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	Optional<Product> findById(Long id);
	
	@Query("SELECT DISTINCT s.value FROM RamSize s")
	List<String> findAllSizes();
	
	@Query("SELECT DISTINCT c.name FROM StorageSize c")
	List<String> findAllCategories();
	
	@Query("SELECT DISTINCT b.name FROM MobileColor b")
	List<String> findAllBrands();
	
}

package com.gsore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.gsore.domain.CartItem;
import com.gsore.domain.User;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	@EntityGraph(attributePaths = { "article" })
	List<CartItem> findAllByUserAndOrderIsNull(User user);
	
	void deleteAllByUserAndOrderIsNull(User user);

	int countDistinctByUserAndOrderIsNull(User user);
}

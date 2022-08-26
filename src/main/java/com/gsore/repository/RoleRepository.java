package com.gsore.repository;

import org.springframework.data.repository.CrudRepository;

import com.gsore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}

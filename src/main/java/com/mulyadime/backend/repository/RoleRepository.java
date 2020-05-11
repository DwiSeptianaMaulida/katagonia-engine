package com.mulyadime.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.backend.models.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
	
	Optional<Role> findOneByName(String name);

}

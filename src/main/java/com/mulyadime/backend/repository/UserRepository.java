package com.mulyadime.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.backend.models.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	Optional<User> findOneByUsername(String username);

}

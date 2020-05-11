package com.mulyadime.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.backend.models.Acronym;

@Repository
public interface AcronymRepository extends PagingAndSortingRepository<Acronym, Long> {

}

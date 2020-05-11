package com.mulyadime.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.backend.models.Term;

@Repository
public interface TermRepository extends PagingAndSortingRepository<Term, Long> {

}

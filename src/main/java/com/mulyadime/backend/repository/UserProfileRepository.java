package com.mulyadime.backend.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.backend.models.UserProfile;

@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, Long> {
	
	List<UserProfile> findByMahasiswa(boolean mahasiswa);
	
	List<UserProfile> findByDosen(boolean dosen);

}

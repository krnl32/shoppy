package com.krnl32.shoppy.repository;

import com.krnl32.shoppy.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

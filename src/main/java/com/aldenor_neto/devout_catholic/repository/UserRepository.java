package com.aldenor_neto.devout_catholic.repository;

import com.aldenor_neto.devout_catholic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String userEmail);

    boolean existsByEmail(String email);
}

package com.pistasien.clothingstore.repository;

import com.pistasien.clothingstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String email);
}

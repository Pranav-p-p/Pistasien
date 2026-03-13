package com.pistasien.clothingstore.repository;

import com.pistasien.clothingstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserPhone(String phone);
    Optional<User> findByUserEmail(String email);
}

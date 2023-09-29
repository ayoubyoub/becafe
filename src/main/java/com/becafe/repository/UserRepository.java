package com.becafe.repository;

import com.becafe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Ayoub Youb
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}

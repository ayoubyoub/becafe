package com.becafe.repository;

import com.becafe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// made by Ayoub Youb with ❤️
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}

package com.becafe.repository;

import com.becafe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// made by Ayoub Youb with ❤️
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}

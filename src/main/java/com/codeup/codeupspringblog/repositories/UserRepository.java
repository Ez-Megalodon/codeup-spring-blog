package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    User findUserByUsername(String username);
}

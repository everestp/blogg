package com.offnine.blogg.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.offnine.blogg.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
package com.offnine.blogg.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.offnine.blogg.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {


}

package com.cemil.evastudycase.repository;

import com.cemil.evastudycase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

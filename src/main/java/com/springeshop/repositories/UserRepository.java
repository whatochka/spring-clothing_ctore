package com.springeshop.repositories;

import com.springeshop.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
    @Override
    @NonNull
    List<User> findAll();
}

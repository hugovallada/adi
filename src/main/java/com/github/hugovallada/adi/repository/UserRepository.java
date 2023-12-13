package com.github.hugovallada.adi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.hugovallada.adi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
}

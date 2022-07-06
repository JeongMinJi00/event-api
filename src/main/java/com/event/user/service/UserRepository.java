package com.event.user.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

package com.project.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.contact.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}

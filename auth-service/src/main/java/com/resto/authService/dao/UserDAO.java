package com.resto.authService.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resto.commonModel.entity.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
	User findByUsername(String username);
}

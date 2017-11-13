package com.erestaurant.service;

import com.erestaurant.model.UserRole;
import com.erestaurant.model.Users;

import java.util.List;
import java.util.Set;

/**
 * Created by Programmer on 30-Oct-17.
 */
public interface UserService {

    Users createUser(Users user, Set<UserRole> userRoles);
    List<Users> findAll();
    Users findOne(Long id);
    Users findUserByEmail(String email);
    Users findByUsername(String username);
    Users save(Users save);

}

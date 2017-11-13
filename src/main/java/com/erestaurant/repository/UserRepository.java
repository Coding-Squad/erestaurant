package com.erestaurant.repository;

/**
 * Created by Programmer on 30-Oct-17.
 */
import java.util.List;

import com.erestaurant.model.Users;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Users, Long>{

    Users findByUsername(String username);
    Users findByEmail(String email);

}
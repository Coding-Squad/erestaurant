package com.erestaurant.service.impl;

import java.util.List;
import java.util.Set;


import com.erestaurant.model.OrderList;
import com.erestaurant.model.Rating;
import com.erestaurant.model.UserRole;
import com.erestaurant.model.Users;
import com.erestaurant.repository.RoleRepository;
import com.erestaurant.repository.UserRepository;
import com.erestaurant.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Transactional
	public Users createUser(Users user, Set<UserRole> userRoles){
		Users localUser = userRepository.findByUsername(user.getUsername());
	if (localUser != null){
		LOG.info("User with username {} already exists", user.getUsername());
	} else {
		for (UserRole ur : userRoles){
			roleRepository.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		
		OrderList orderList = new OrderList();
		orderList.setUser(user);
		user.setOrderList(orderList);
		
		Rating rating = new Rating();
		rating.setUser(user);
		user.setRating(rating);
		
		localUser = userRepository.save(user);
	}
	return localUser;
	}
	
	@Override
	public Users save(Users user){
		return userRepository.save(user);
	}

	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<Users> findAll() {
		List<Users> listUser = (List<Users>) userRepository.findAll();
		return listUser;
	}

	@Override
	public Users findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Users findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}

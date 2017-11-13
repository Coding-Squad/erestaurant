package com.erestaurant.rest;

import java.security.Principal;

import java.util.List;

import com.erestaurant.model.Rating;
import com.erestaurant.model.Users;
import com.erestaurant.service.RatingService;
import com.erestaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserRest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RatingService ratingService;
	
	@RequestMapping("/listUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Users> getListUser(){
		return userService.findAll();
	}
	
	@RequestMapping("/{id}")
	public Users getUser(@PathVariable("id") Long id){
		Users user = userService.findOne(id);
		return user;
	}
	
	@RequestMapping("/rating/")
	public Rating getRate(Principal principal){
		Users user = userService.findByUsername(principal.getName());
		Rating rating = user.getRating();
		return rating;
	}
	
	@RequestMapping("/userRating/{id}")
	public Rating getUserRate(@PathVariable("id") Long id){
		Users user = userService.findOne(id);
		Rating rating = user.getRating();
		return rating;
	}
	
	@RequestMapping(value="/rate", method=RequestMethod.POST)
	public ResponseEntity rate(@RequestBody Rating rating, Principal principal){
		
		Users user = userService.findByUsername(principal.getName());
		Rating ratingAVG = ratingService.findOne(user.getId());
		ratingAVG.setFood(rating.getFood());
		ratingAVG.setService(rating.getService());
		ratingAVG.setPrice(rating.getPrice());
		user.setRating(ratingAVG);
		ratingAVG.setUser(user);
		userService.save(user);
		
		return new ResponseEntity("Rated!", HttpStatus.OK);
	}
	
}

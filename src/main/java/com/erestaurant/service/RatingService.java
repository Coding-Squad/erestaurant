package com.erestaurant.service;


import com.erestaurant.model.Rating;

public interface RatingService {
	
	Rating save(Rating rating);
	Rating findOne(Long id);

}

package com.erestaurant.service.impl;

import com.erestaurant.model.Rating;
import com.erestaurant.repository.RatingRepository;
import com.erestaurant.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating save(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public Rating findOne(Long id) {		
		return ratingRepository.findOne(id);
	}

}

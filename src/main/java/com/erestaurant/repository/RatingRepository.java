package com.erestaurant.repository;

import com.erestaurant.model.Rating;
import org.springframework.data.repository.CrudRepository;


public interface RatingRepository extends CrudRepository<Rating, Long> {

}

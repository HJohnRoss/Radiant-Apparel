package com.radiantapparel.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Review;
import com.radiantapparel.project.Repositories.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> allReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> productReviews(Long productId) {
        return reviewRepository.findByProductIdIs(productId);
    }

    public Review createReview(Review r) {
        return reviewRepository.save(r);
    }

    public void deleteReview(Review r) {
        reviewRepository.delete(r);
    }
}

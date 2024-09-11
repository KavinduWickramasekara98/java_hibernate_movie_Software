package com.gmdb.service;

import com.gmdb.dao.ReviewDAO;
import com.gmdb.entity.Movie;
import com.gmdb.entity.Review;

public class ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewService() {
        reviewDAO = new ReviewDAO();
    }

    public void addReview(Review review) {
        reviewDAO.addReview(review);
    }

    public double getAverageRating(Movie movie) {
        return reviewDAO.getAverageRating(movie);
    }
}

package com.abhijeet.jobsite.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);

    boolean addReview(long companyId, Review review);

    Review getReview(long companyId, long reviewId);

    Review updateReview(long companyId, long reviewId, Review updatedReview);

    boolean deleteReview(long companyId, long reviewId);
}

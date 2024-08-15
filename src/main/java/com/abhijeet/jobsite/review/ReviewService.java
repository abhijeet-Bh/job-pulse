package com.abhijeet.jobsite.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);

    boolean addReview(long companyId, Review review);
}

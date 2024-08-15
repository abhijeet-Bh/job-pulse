package com.abhijeet.jobsite.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable long companyId, @RequestBody Review review) {
        boolean isAdded = reviewService.addReview(companyId, review);
        if (isAdded)
            return new ResponseEntity<>("Review Added successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Company Not Found - Failed to add review!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable long companyId, @PathVariable long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        if (review != null) return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReviews(@PathVariable long companyId,
                                                @PathVariable long reviewId,
                                                @RequestBody Review updatedReview) {
        Review review = reviewService.updateReview(companyId, reviewId, updatedReview);
        if (review != null) return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable long companyId,
                                               @PathVariable long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted) return new ResponseEntity<>("Review deleted!", HttpStatus.OK);
        return new ResponseEntity<>("Failed to delete review!", HttpStatus.BAD_REQUEST);
    }
}

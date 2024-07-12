package dev.bashar.movie.Services;

import dev.bashar.movie.Movie;
import dev.bashar.movie.Repository.MovieRepository;
import dev.bashar.movie.Review;
import dev.bashar.movie.Repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewsBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewsBody));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId()))
                .first();

        return review;
    }

    public void deleteReview(String reviewId) {
        ObjectId objectId = new ObjectId(reviewId);

        // Remove the review from the repository
        reviewRepository.deleteById(objectId);

        // Remove the reference from the associated movie
        mongoTemplate.updateMulti(
                new Query(Criteria.where("reviewIds").is(objectId)),
                new Update().pull("reviewIds", objectId),
                Movie.class
        );
    }
    public Review editReview(String reviewId, String newBody) {
        ObjectId objectId = new ObjectId(reviewId);

        // Find the review
        Review review = reviewRepository.findById(objectId).orElseThrow(() -> new RuntimeException("Review not found"));

        // Update the review's body
        review.setBody(newBody);

        // Save the updated review
        return reviewRepository.save(review);
    }
    public Optional<Review> getReview(String reviewId) {
        ObjectId objectId = new ObjectId(reviewId);
        return reviewRepository.findById(objectId);
    }

}

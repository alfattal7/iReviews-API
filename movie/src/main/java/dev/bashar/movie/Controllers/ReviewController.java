package dev.bashar.movie.Controllers;

import dev.bashar.movie.Review;
import dev.bashar.movie.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/comp/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewsBody"), payload.get("imdbId")), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> editReview(@PathVariable String id, @RequestBody Map<String, String> payload) {
        Review updatedReview = reviewService.editReview(id, payload.get("reviewsBody"));
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable String id) {
        Optional<Review> review = reviewService.getReview(id);
        return review.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

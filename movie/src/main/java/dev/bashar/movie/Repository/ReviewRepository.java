package dev.bashar.movie.Repository;

import dev.bashar.movie.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

    void deleteById(ObjectId reviewId);


    void deleteById(String reviewId);
}

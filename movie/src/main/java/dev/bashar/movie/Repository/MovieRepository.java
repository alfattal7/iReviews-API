package dev.bashar.movie.Repository;

import dev.bashar.movie.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin("*")
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    Optional<Movie> findTheMovieByImdbId(String imdbId);
}

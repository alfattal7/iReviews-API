package dev.bashar.movie.Services;

import dev.bashar.movie.Movie;
import dev.bashar.movie.Repository.MovieRepository;
import dev.bashar.movie.Repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin("*")
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired

    public List<Movie> showAllMovies(){
      return movieRepository.findAll();

    }

    public Optional<Movie> showSingelMovie(String imdbId){
        return movieRepository.findTheMovieByImdbId(imdbId);

    }
    public void deleteMovie(String movieId) {
        ObjectId objectId = new ObjectId(movieId);

        // Find the movie by ID
        Movie movie = movieRepository.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));


        // Remove the movie from the repository
        movieRepository.deleteById(objectId);
    }
    public Movie editMovie(String movieId, Movie updatedMovie) {
        ObjectId objectId = new ObjectId(movieId);

        // Find the movie by ID
        Movie existingMovie = movieRepository.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Update fields of the existing movie
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setReleaseData(updatedMovie.getReleaseData());
        existingMovie.setPoster(updatedMovie.getPoster());
        existingMovie.setTrailerLink(updatedMovie.getTrailerLink());
        existingMovie.setGenres(updatedMovie.getGenres());
        existingMovie.setBackdrops(updatedMovie.getBackdrops());
        existingMovie.setReviewIds(updatedMovie.getReviewIds());

        // Save the updated movie
        return movieRepository.save(existingMovie);
    }
    public Movie addMovie(Movie movie) {
        movie.setId(ObjectId.get()); // Generate a new ObjectId
        return movieRepository.insert(movie);
    }
}

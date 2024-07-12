package dev.bashar.movie.Controllers;

import dev.bashar.movie.Movie;
import dev.bashar.movie.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comp/movies")
@CrossOrigin("*")
public class MoviesController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.showAllMovies(),HttpStatus.OK);
    }
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingelMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.showSingelMovie(imdbId),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movie> editMovie(@PathVariable String id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.editMovie(id, movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }
}





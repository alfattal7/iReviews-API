package dev.bashar.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin("*")
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseData;
    private String poster;
    private String trailerLink;
    private List<String> genres;
    private List<String> backdrops;
   @DocumentReference
    private List<Review> reviewIds;

}

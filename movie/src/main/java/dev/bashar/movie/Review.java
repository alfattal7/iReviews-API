package dev.bashar.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin("*")
public class Review {
    private String id;
    private String body;

    public Review(String body) {
        this.body = body;
    }
}

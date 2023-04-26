package com.rbp.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private ObjectId _id;
    private String movieName;

    private String theatreName;
    private Integer noOfTicketsAvailable;

    public Movie(String movieName, String theatreName, Integer noOfTicketsAvailable, String ticketsStatus) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.noOfTicketsAvailable = noOfTicketsAvailable;
        this.ticketsStatus = ticketsStatus;
    }

    private String ticketsStatus;

    public Movie(String movieName, String theatreName, Integer noOfTicketsAvailable) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.noOfTicketsAvailable = noOfTicketsAvailable;
    }

    public Movie(ObjectId _id, String movieName, String theatreName, Integer noOfTicketsAvailable) {
        this._id = _id;
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.noOfTicketsAvailable = noOfTicketsAvailable;
    }
}

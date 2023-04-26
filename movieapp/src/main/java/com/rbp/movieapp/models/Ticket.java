package com.rbp.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "ticket")
@Data@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private ObjectId _id;
    private String loginId;

    public Ticket(String loginId, String movieName, String theatreName, Integer noOfTickets, List<String> seatNumber) {
        this.loginId = loginId;
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.noOfTickets = noOfTickets;
        this.seatNumber = seatNumber;
    }

    private String movieName;
    private String theatreName;
    private Integer noOfTickets;
    private List<String> seatNumber;
}

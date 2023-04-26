package com.rbp.movieapp.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rbp.movieapp.models.Ticket;
import com.rbp.movieapp.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rbp.movieapp.models.Movie;
import com.rbp.movieapp.repository.MovieRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceTest {

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private MovieService movieService;

    @Test
    public void testGetAllMovies() {
        // Set up mock data
        List<Movie> movies = new ArrayList<>();
        Movie movie1 = new Movie("Movie 1", "Theatre 1", 120, "Book now");
        Movie movie2 = new Movie("Movie 2", "Theatre 2", 150, "Book now");
        movies.add(movie1);
        movies.add(movie2);

        // Set up mock behavior
        when(movieRepository.findAll()).thenReturn(movies);

        // Call the method under test
        List<Movie> result = movieService.getAllMovies();

        // Assert the result
        assertEquals(movies, result);
    }

    @Test
    void getMovieByName() {
        // Set up mock data
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Dasara", "Miraj", 126, "Book ASAP"));
        movies.add(new Movie("The Matrix", "Hollywood", 150, "Experience the action"));
        movies.add(new Movie("Inception", "Hollywood", 148, "Your mind is the scene of the crime"));
        when(movieRepository.findByMovieName("Matrix")).thenReturn(
                movies.stream().filter(m -> m.getMovieName().equals("The Matrix")).collect(Collectors.toList())
        );

        // Call the method being tested
        List<Movie> result = movieService.getMovieByName("Matrix");

        // Assert the results
        assertEquals(1, result.size());
        assertEquals("The Matrix", result.get(0).getMovieName());
    }

    @Test
    void findSeats() {
        // Set up mock data
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("chandan","The Matrix", "Screen 1", 2, new ArrayList<String>(List.of("a1","a2"))));
        when(ticketRepository.findSeats("The Matrix", "Screen 1")).thenReturn(tickets);

        // Call the method being tested
        List<Ticket> result = movieService.findSeats("The Matrix", "Screen 1");

        // Assert the results
        assertEquals(1, result.size());
    }

    @Test
    void testFindAvailableTickets() {
        String movieName = "Avengers: Endgame";
        String theatreName = "Theatre 1";
        List<Movie> expectedMovies = Arrays.asList(
                new Movie("Avengers: Endgame", "Action", 180, "Incredible!"),
                new Movie("Avengers: Endgame", "Action", 180, "Amazing!"),
                new Movie("Avengers: Endgame", "Action", 180, "Thrilling!")
        );
        when(movieRepository.findAvailableTickets(movieName, theatreName)).thenReturn(expectedMovies);
        List<Movie> actualMovies = movieService.findAvailableTickets(movieName, theatreName);
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testSaveTicket() {
        Ticket expectedTicket = new Ticket("chandan","The Matrix", "Screen 1", 2, new ArrayList<String>(List.of("a1","a2")));
        movieService.saveTicket(expectedTicket);
        verify(ticketRepository, times(1)).save(expectedTicket);
    }

    @Test
    void testSaveMovie() {
        Movie expectedMovie = new Movie("Avengers: Endgame", "Action", 180, "Incredible!");
        movieService.saveMovie(expectedMovie);
        verify(movieRepository, times(1)).save(expectedMovie);
    }

    @Test
    void testGetAllBookedTickets() {
        String movieName = "Avengers: Endgame";
        List<Ticket> expectedTickets = Arrays.asList(
                new Ticket("chandan","The Matrix", "Screen 1", 2, new ArrayList<String>(List.of("a1","a2")))
        );
        when(ticketRepository.findByMovieName(movieName)).thenReturn(expectedTickets);
        List<Ticket> actualTickets = movieService.getAllBookedTickets(movieName);
        assertEquals(expectedTickets, actualTickets);
    }

    @Test
    void findByMovieName() {
        String movieName = "The Dark Knight";
        List<Movie> movies = new ArrayList<>(List.of(
                new Movie("The Dark Knight", "Christopher Nolan", 152, "A Batman movie"),
                new Movie("The Dark Knight Rises", "Christopher Nolan", 165, "Another Batman movie")
        ));
        when(movieRepository.findByMovieName(movieName)).thenReturn(movies);
        assertEquals(movies, movieService.findByMovieName(movieName));
    }

    @Test
    void deleteByMovieName() {
        String movieName = "The Dark Knight";
        movieService.deleteByMovieName(movieName);
        verify(movieRepository, times(1)).deleteByMovieName(movieName);
    }

}

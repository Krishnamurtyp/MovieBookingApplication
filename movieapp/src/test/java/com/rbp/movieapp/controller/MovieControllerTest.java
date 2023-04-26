//package com.rbp.movieapp.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.rbp.movieapp.exception.MoviesNotFound;
//import com.rbp.movieapp.models.ERole;
//import com.rbp.movieapp.models.Movie;
//import com.rbp.movieapp.models.Role;
//import com.rbp.movieapp.models.User;
//import com.rbp.movieapp.payload.request.LoginRequest;
//import com.rbp.movieapp.repository.UserRepository;
//import com.rbp.movieapp.security.services.MovieService;
//import org.bson.types.ObjectId;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class MovieControllerTest {
//
//    @Mock
//    private MovieService movieService;
//
//    @InjectMocks
//    private MovieController movieController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private String jwtToken;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void testGetAllMoviesWithoutToken() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/all"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testGetAllMovies() throws Exception {
//
//        // Create a mock movie service that returns dummy data
//        MovieService movieServiceMock = mock(MovieService.class);
//        List<Movie> allMovies = new ArrayList<>();
//        allMovies.add(new Movie(
//                new ObjectId(),
//                "Dasara",
//                "Miraj",
//                120,
//                "ASAP"
//        ));
//        when(movieService.getAllMovies()).thenReturn(allMovies);
//        authenticate();
//        // Inject the movie service mock into the controller and perform the request
//        MovieController movieController = new MovieController(movieService);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/all"))
//                .andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
//    }
//
//    @Test
//    public void testGetMovieByNameReturnsListOfMovies() throws Exception {
//        String movieName = "Avengers";
//        List<Movie> movieList = new ArrayList<>();
//        movieList.add(new Movie(new ObjectId("617bcf3d21de700d5819f0f7"), "Avengers", "Theatre 1", 100));
//        movieList.add(new Movie(new ObjectId("617bcf3d21de700d5819f0f8"), "Avengers", "Theatre 2", 120));
//        when(movieService.getMovieByName(movieName)).thenReturn(movieList);
//
//        authenticate();
//
//        MovieController movieController = new MovieController(movieService);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/movies/search/Avengers"))
//                .andExpect(status().isFound());
//
//    }
//    @Test(expected = MoviesNotFound.class)
//    public void testGetMovieByNameReturnsNoMovies() throws Exception {
//        String movieName = "Project-k";
//        List<Movie> movieList = new ArrayList<>();
//        movieList.add(new Movie(new ObjectId("617bcf3d21de700d5819f0f7"), "Project-k", "Theatre 1", 100));
//        when(movieService.getMovieByName(movieName)).thenReturn(movieList);
//        authenticate();
//        MovieController movieController = new MovieController(movieService);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/movies/search/Avengers"))
//                .andExpect(status().isNotFound());
//    }
//
//    private void authenticate(){
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//}
//

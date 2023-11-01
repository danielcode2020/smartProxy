package com.example.eurekaclient;

import com.example.eurekaclient.domain.Movie;
import com.example.eurekaclient.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class EurekaClientApplicationTests {

    public static final String MOVIE_UUID = "28b0fa3f-2623-45a4-8b55-12c6b804ba61";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testSaveMovie() throws Exception {
        // Create a sample movie object to send in the request
        Movie movie = new Movie();
        movie.setUuid(UUID.fromString(MOVIE_UUID));
        movie.setName("Test Movie");
        movie.setDescription("Test Description");
        movie.setActors(List.of("TestGatman", "TestMarandici", "TestStaci"));
        movie.setBudget(BigDecimal.TEN);
        // Convert the movie object to JSON
        String jsonMovie = new ObjectMapper().writeValueAsString(movie);

        // Send a POST request to the "/your-endpoint-here" URL with the JSON data
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMovie));
        boolean result = movieRepository.existsById(UUID.fromString(MOVIE_UUID));
        assertTrue(result);
    }

    @Test
    public void testDeleteMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies")
                        .param("id", MOVIE_UUID))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the movie has been deleted by checking if it no longer exists in the repository
        boolean movieExists = movieRepository.existsById(UUID.fromString(MOVIE_UUID));

        // Assert that the movie no longer exists
        assertFalse(movieExists);
    }

    @Test
    public void addMovieWithoutId() throws Exception{
        // Create a sample movie object to send in the request
        Movie movie = new Movie();
        movie.setName("Test Movie");
        movie.setDescription("Test Description");
        movie.setActors(List.of("TestGatman", "TestMarandici", "TestStaci"));
        movie.setBudget(BigDecimal.TEN);
        // Convert the movie object to JSON
        String jsonMovie = new ObjectMapper().writeValueAsString(movie);

        // Send a POST request to the "/your-endpoint-here" URL with the JSON data
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMovie));
        boolean result = movieRepository.existsById(UUID.fromString(MOVIE_UUID));
        assertTrue(result);
    }



}

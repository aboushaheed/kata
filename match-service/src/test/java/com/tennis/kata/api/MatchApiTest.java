package com.tennis.kata.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import com.tennis.kata.domaine.match.model.CreateMatchResponse;
import com.tennis.kata.domaine.match.model.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MatchApi.class)
class MatchApiTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MatchService matchService;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext) {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testCreateMatchSuccess() throws Exception {
    // Arrange
    String matchConfigJson = "{\"matchConfig\":\"ABBA\"}";
    CreateMatchResponse expectedResponse = new CreateMatchResponse("ABBA", "the game is in progress...");

    given(matchService.create(any(CreateMatchRequest.class))).willReturn(expectedResponse);

    // Act & Assert
    mockMvc.perform(post("/matchs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(matchConfigJson))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"matchConfig\":\"ABBA\",\"winner\":\"the game is in progress...\"}"));

    verify(matchService, times(1)).create(any(CreateMatchRequest.class));
  }

  @Test
  public void testCreateMatchWithInvalidRequest() throws Exception {
    // Arrange
    String invalidMatchConfigJson = "{}";

    // Act & Assert
    mockMvc.perform(post("/matchs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidMatchConfigJson))
        .andExpect(status().isBadRequest());

    verify(matchService, times(0)).create(any(CreateMatchRequest.class));
  }

  @Test
  public void testCreateMatchResponseHeaders() throws Exception {
    // Arrange
    String matchConfigJson = "{\"matchConfig\":\"ABBA\"}";
    CreateMatchResponse expectedResponse = new CreateMatchResponse("ABBA", "the game is in progress...");

    given(matchService.create(any(CreateMatchRequest.class))).willReturn(expectedResponse);

    // Act & Assert
    mockMvc.perform(post("/matchs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(matchConfigJson))
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Type", "application/json"));
  }
}
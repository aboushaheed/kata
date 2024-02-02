package com.tennis.kata.domaine.match.model;

import com.tennis.kata.adapters.secondary.KafkaMessagePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

  @Mock
  private KafkaMessagePublisher kafkaMessagePublisher;

  @InjectMocks
  private MatchService matchService;

  private CreateMatchRequest createMatchRequest;
  private CreateMatchResponse expectedResponse;

  @BeforeEach
  void setUp() {
    createMatchRequest = new CreateMatchRequest("sampleMatchConfig");
    expectedResponse = CreateMatchResponse.builder()
        .matchConfig("sampleMatchConfig")
        .winner("the game is in progress...")
        .build();
  }

  @Test
  void testCreateSuccess() {
    // Arrange
    doNothing().when(kafkaMessagePublisher).publishMatchConfig(any(CreateMatchRequest.class));

    // Act
    CreateMatchResponse response = matchService.create(createMatchRequest);

    // Assert
    assertNotNull(response);
    assertEquals(expectedResponse.getMatchConfig(), response.getMatchConfig());
    assertEquals(expectedResponse.getWinner(), response.getWinner());
    verify(kafkaMessagePublisher, times(1)).publishMatchConfig(createMatchRequest);
  }

  @Test
  void testCreateWhenPublisherThrowsException() {
    // Arrange
    doThrow(new RuntimeException("Error publishing match config")).when(kafkaMessagePublisher).publishMatchConfig(any(CreateMatchRequest.class));

    // Act & Assert
    Exception exception = assertThrows(RuntimeException.class, () -> matchService.create(createMatchRequest));
    assertEquals("Error publishing match config", exception.getMessage());

    // Verify behavior
    verify(kafkaMessagePublisher, times(1)).publishMatchConfig(createMatchRequest);
  }
  @Test
  void testCreateWithNullRequest() {
    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> matchService.create(null), "Request can not be null");

    verify(kafkaMessagePublisher, never()).publishMatchConfig(any(CreateMatchRequest.class));
  }

  @Test
  void testCreateWithInvalidMatchConfig() {
    // Arrange
    CreateMatchRequest invalidRequest = new CreateMatchRequest(""); // Assuming empty string is invalid

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> matchService.create(invalidRequest), "match config can not be empty");

    verify(kafkaMessagePublisher, never()).publishMatchConfig(eq(invalidRequest));
  }

  @Test
  void testPublishMatchConfigCalledBeforeCreatingResponse() {
    // Arrange
    InOrder inOrder = inOrder(kafkaMessagePublisher);

    // Act
    matchService.create(createMatchRequest);

    // Assert
    inOrder.verify(kafkaMessagePublisher).publishMatchConfig(createMatchRequest);
  }

}
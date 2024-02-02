package com.tennis.kata.adapters.secondary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tennis.kata.adapters.secondary.mapper.KafkaMatchConfigMapper;
import com.tennis.kata.adapters.secondary.model.ConfigMessage;
import com.tennis.kata.adapters.secondary.model.MatchConfigMessage.MatchData;
import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

@ExtendWith(MockitoExtension.class)
class KafkaMessagePublisherTest {

  @Mock
  private Source source;

  @Mock
  private KafkaMatchConfigMapper kafkaMatchConfigMapper;

  @Mock
  private MessageChannel messageChannel;

  @InjectMocks
  private KafkaMessagePublisher kafkaMessagePublisher;

  @BeforeEach
  public void setUp() {
    when(source.output()).thenReturn(messageChannel);
    when(messageChannel.send(any())).thenReturn(true);
  }

  @Test
  void publishMatchConfigSuccessfully() {
    // Arrange
    CreateMatchRequest createMatchRequest = new CreateMatchRequest("ABBBA");
    ConfigMessage mappedConfig = new ConfigMessage();
    MatchData matchData = new MatchData();
    matchData.setMatchConfig("ABBBA");
    mappedConfig.setGame(matchData);
    when(kafkaMatchConfigMapper.mapMatchConfig(createMatchRequest)).thenReturn(mappedConfig);

    // Act
    kafkaMessagePublisher.publishMatchConfig(createMatchRequest);

    // Assert
    verify(kafkaMatchConfigMapper, times(1)).mapMatchConfig(createMatchRequest);
    verify(source.output(), times(1)).send(any());
  }

  @Test
  void publishMatchConfigFailureToSend() {
    // Arrange
    CreateMatchRequest createMatchRequest = new CreateMatchRequest("ABBBA");
    ConfigMessage mappedConfig = new ConfigMessage();
    MatchData matchData = new MatchData();
    matchData.setMatchConfig("ABBBA");
    mappedConfig.setGame(matchData);
    when(kafkaMatchConfigMapper.mapMatchConfig(createMatchRequest)).thenReturn(mappedConfig);
    when(messageChannel.send(any())).thenReturn(false);

    // Act
    kafkaMessagePublisher.publishMatchConfig(createMatchRequest);

    // Assert
    verify(kafkaMatchConfigMapper, times(1)).mapMatchConfig(createMatchRequest);
    verify(source.output(), times(1)).send(any());
  }
}
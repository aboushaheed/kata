package com.tennis.kata.domaine.match.model;

import com.tennis.kata.adapters.secondary.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

  private final KafkaMessagePublisher kafkaMessagePublisher;

  @Autowired
  public MatchService(KafkaMessagePublisher kafkaMessagePublisher) {
    this.kafkaMessagePublisher = kafkaMessagePublisher;
  }

  public CreateMatchResponse create(CreateMatchRequest createMatchRequest) {
    if(createMatchRequest == null) throw new IllegalArgumentException("Request can not be null");
    if(createMatchRequest.getMatchConfig() == null) throw new IllegalArgumentException("Match config can not be null");
    if(createMatchRequest.getMatchConfig().isEmpty()) throw new IllegalArgumentException("Match config can not be empty");
    kafkaMessagePublisher.publishMatchConfig(createMatchRequest);
    return CreateMatchResponse.builder()
        .matchConfig(createMatchRequest.getMatchConfig())
        .winner("the game is in progress...")
        .build();
  }
}

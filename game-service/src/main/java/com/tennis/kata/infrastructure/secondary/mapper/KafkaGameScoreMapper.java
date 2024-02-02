package com.tennis.kata.infrastructure.secondary.mapper;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.secondary.model.GameScoreChangeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class KafkaGameScoreMapper {

	private final KafkaMessagesMapper kafkaMessagesMapper;

	@Autowired
	public KafkaGameScoreMapper(KafkaMessagesMapper kafkaMessagesMapper) {
		this.kafkaMessagesMapper = kafkaMessagesMapper;
	}

	public GameScoreChangeMessage mapGameScoreChange(Game game) {

		GameScoreChangeMessage gameScoreChangeMessage = new GameScoreChangeMessage();
		gameScoreChangeMessage.setId(UUID.randomUUID());
		gameScoreChangeMessage.setDate(LocalDateTime.now());
		gameScoreChangeMessage.setGame(kafkaMessagesMapper.toGameMessageGameData(game));

		return gameScoreChangeMessage;
	}
}

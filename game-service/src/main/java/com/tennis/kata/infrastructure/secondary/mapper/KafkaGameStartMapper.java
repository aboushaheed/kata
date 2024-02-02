package com.tennis.kata.infrastructure.secondary.mapper;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.secondary.model.GameStartMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class KafkaGameStartMapper {

	private final KafkaMessagesMapper kafkaMessagesMapper;

	@Autowired
	public KafkaGameStartMapper(KafkaMessagesMapper kafkaMessagesMapper) {
		this.kafkaMessagesMapper = kafkaMessagesMapper;
	}

	public GameStartMessage mapStartMessage(Game game) {

		GameStartMessage gameStartMessage = new GameStartMessage();
		gameStartMessage.setId(UUID.randomUUID());
		gameStartMessage.setDate(LocalDateTime.now());
		gameStartMessage.setGame(kafkaMessagesMapper.toGameMessageGameData(game));

		return gameStartMessage;
	}



}

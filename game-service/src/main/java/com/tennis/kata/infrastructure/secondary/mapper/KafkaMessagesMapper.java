package com.tennis.kata.infrastructure.secondary.mapper;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.secondary.model.GameMessage.GameData;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagesMapper {

	public GameData toGameMessageGameData(Game game) {
		GameData gameData = new GameData();

		gameData.setWinner(game.getWinner());
		gameData.setMatchConfig(game.getMatchConfig());
		return gameData;
	}
}

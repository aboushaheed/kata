package com.tennis.kata.infrastructure.secondary.mapper;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.secondary.model.GameHasWinnerMessage;
import com.tennis.kata.infrastructure.secondary.model.GameWinnerMessage.GameData;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class KafkaGameHasWinnerMapper {


	public GameHasWinnerMessage mapHasWinnerMessage(Game game) {
		GameData winnerData = new GameData();

		winnerData.setWinner(game.getWinner());
		winnerData.setMatchConfig(game.getMatchConfig());
		GameHasWinnerMessage gameHasWinnerMessage = new GameHasWinnerMessage();
		gameHasWinnerMessage.setId(UUID.randomUUID());
		gameHasWinnerMessage.setDate(LocalDateTime.now());
		gameHasWinnerMessage.setGame(winnerData);

		return gameHasWinnerMessage;
	}

}

package com.tennis.kata.infrastructure.secondary.mapper;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.secondary.model.MatchHasWinnerMessage;
import com.tennis.kata.infrastructure.secondary.model.MatchWinnerMessage.MatchData;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class KafkaMatchHasWinnerMapper {

	public MatchHasWinnerMessage mapMatchHasWinner(Game tennisMatch) {

		MatchData matchData = new MatchData();
		matchData.setWinner(tennisMatch.getWinner());

		MatchHasWinnerMessage matchHasWinnerMessage = new MatchHasWinnerMessage();
		matchHasWinnerMessage.setId(UUID.randomUUID());
		matchHasWinnerMessage.setDate(LocalDateTime.now());
		matchHasWinnerMessage.setMatch(matchData);

			return matchHasWinnerMessage;
	}
}

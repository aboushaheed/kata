package com.tennis.kata.adapters.secondary.mapper;

import com.tennis.kata.adapters.secondary.model.ConfigMessage;
import com.tennis.kata.adapters.secondary.model.MatchConfigMessage.MatchData;
import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class KafkaMatchConfigMapper {


	public ConfigMessage mapMatchConfig(CreateMatchRequest createMatchRequest) {
		MatchData matchData = new MatchData();
		matchData.setMatchConfig(createMatchRequest.getMatchConfig());
		matchData.setWinner("The match is starting...");
		ConfigMessage configMessage = new ConfigMessage();
		configMessage.setId(UUID.randomUUID());
		configMessage.setDate(LocalDateTime.now());
		configMessage.setGame(matchData);

		return configMessage;
	}

}

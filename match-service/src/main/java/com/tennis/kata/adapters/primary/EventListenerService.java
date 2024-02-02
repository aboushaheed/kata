package com.tennis.kata.adapters.primary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.kata.adapters.primary.model.GameHasWinnerEvent;
import com.tennis.kata.adapters.primary.model.GameScoreChangeEvent;
import com.tennis.kata.adapters.primary.model.MatchConfigAndStartEvent;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventListenerService {

	private final ObjectMapper mapper;

	public EventListenerService(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public void handleEvent(String context) {
		log.info(context);
		try {
			processEvent(context);
		} catch (Exception e) {
			log.error("Can not process this event", e);
		}
	}


	private void processEvent(String context) throws IOException {
		GameEvent evenement = mapper.readValue(context, GameEvent.class);
		if (MatchConfigAndStartEvent.TYPE.equals(evenement.getType())) {
			handleStartGameEvent(context);
		}
		if (GameScoreChangeEvent.TYPE.equals(evenement.getType())) {
			handleGameScoreChangeEvent(context);
		}
		if (GameHasWinnerEvent.TYPE.equals(evenement.getType())) {
			handleGameHasWinnerEvent(context);
		}

	}

	private void handleGameHasWinnerEvent(String context) throws JsonProcessingException {
		GameHasWinnerEvent gameHasWinnerEvent		= mapper.readValue(context, GameHasWinnerEvent.class);
		log.info("Game Winner : {} ", gameHasWinnerEvent.getGame().getWinner());
	}

	private void handleGameScoreChangeEvent(String context) throws JsonProcessingException {
		GameScoreChangeEvent gameScoreChangeEvent = mapper.readValue(context, GameScoreChangeEvent.class);
		log.info("Score : {} ", gameScoreChangeEvent.getGame().getWinner());
	}


	private void handleStartGameEvent(String context) throws IOException {
		MatchConfigAndStartEvent matchConfigAndStartEvent = mapper.readValue(context, MatchConfigAndStartEvent.class);
		log.info("Game Started with config  : {} ", matchConfigAndStartEvent.getGame().getMatchConfig());
	}

}

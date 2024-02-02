package com.tennis.kata.infrastructure.primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.kata.domaine.game.GameService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventListenerService {

	private final ObjectMapper mapper;
	private final GameService gameService;

	public EventListenerService(ObjectMapper mapper, GameService gameService) {
		this.mapper = mapper;
		this.gameService = gameService;
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
	}

	private void handleStartGameEvent(String context) throws IOException {
		MatchConfigAndStartEvent matchConfigAndStartEvent = mapper.readValue(context, MatchConfigAndStartEvent.class);
		gameService.play(matchConfigAndStartEvent);

	}

}

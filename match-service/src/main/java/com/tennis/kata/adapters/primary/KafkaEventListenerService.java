package com.tennis.kata.adapters.primary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Sink.class)
@Slf4j
public class KafkaEventListenerService {
	private final EventListenerService eventListenerService;

	public KafkaEventListenerService(EventListenerService eventListenerService) {
		this.eventListenerService = eventListenerService;
	}

	@StreamListener(Sink.INPUT)
	public void handleEvent(String context) {
		eventListenerService.handleEvent(context);
	}
}

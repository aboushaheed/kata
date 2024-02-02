package com.tennis.kata.adapters.secondary;

 import com.tennis.kata.domaine.match.model.CreateMatchRequest;
 import com.tennis.kata.infrastructure.secondary.PublisherPort;
 import com.tennis.kata.adapters.secondary.mapper.KafkaMatchConfigMapper;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
@Slf4j
public class KafkaMessagePublisher implements PublisherPort {

	private final Source source;
	private final KafkaMatchConfigMapper kafkaMatchConfigMapper;

	@Autowired
	public KafkaMessagePublisher(
		Source source,  KafkaMatchConfigMapper kafkaMatchConfigMapper) {
		this.source = source;
		this.kafkaMatchConfigMapper = kafkaMatchConfigMapper;
	}

	@Override
	public void publishMatchConfig(CreateMatchRequest createMatchRequest) {
		log.info("creation of a new match : {}",createMatchRequest);
		source.output().send(MessageBuilder.withPayload(kafkaMatchConfigMapper.mapMatchConfig(createMatchRequest)).build());

	}
}

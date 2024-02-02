package com.tennis.kata.adapters.secondary;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.secondary.HelloMessage;
import com.tennis.kata.infrastructure.secondary.PublisherPort;
import com.tennis.kata.infrastructure.secondary.mapper.KafkaGameHasWinnerMapper;
import com.tennis.kata.infrastructure.secondary.mapper.KafkaGameScoreMapper;
import com.tennis.kata.infrastructure.secondary.mapper.KafkaGameStartMapper;
import com.tennis.kata.infrastructure.secondary.mapper.KafkaMatchHasWinnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class KafkaMessagePublisher implements PublisherPort {

	private final Source source;
	private final KafkaGameStartMapper kafkaGameStartMapper;
	private final KafkaGameScoreMapper kafkaGameScoreMapper;
	private final KafkaGameHasWinnerMapper kafkaGameHasWinnerMapper;
	private final KafkaMatchHasWinnerMapper kafkaMatchHasWinnerMapper;
	@Autowired
	public KafkaMessagePublisher(
		Source source, KafkaGameStartMapper kafkaGameStartMapper, KafkaGameScoreMapper kafkaGameScoreMapper, KafkaGameHasWinnerMapper kafkaGameHasWinnerMapper, KafkaMatchHasWinnerMapper kafkaMatchHasWinnerMapper) {
		this.source = source;
		this.kafkaGameStartMapper = kafkaGameStartMapper;
		this.kafkaGameScoreMapper = kafkaGameScoreMapper;
		this.kafkaGameHasWinnerMapper = kafkaGameHasWinnerMapper;
		this.kafkaMatchHasWinnerMapper = kafkaMatchHasWinnerMapper;
	}

	@Override
	public void publishHello() {
		source.output().send(MessageBuilder.withPayload(new HelloMessage()).build());
	}

	@Override
	public void publishStartGame(Game game) {
		source.output().send(MessageBuilder.withPayload(kafkaGameStartMapper.mapStartMessage(game)).build());
	}

	@Override
	public void publishGameScoreChange(Game game) {
		source.output().send(MessageBuilder.withPayload(kafkaGameScoreMapper.mapGameScoreChange(game)).build());
	}

	@Override
	public void publishGameHasWinner(Game game) {
		source.output().send(MessageBuilder.withPayload(kafkaGameHasWinnerMapper.mapHasWinnerMessage(game)).build());
	}

	@Override
	public void publishMatchHasWinner(Game tennisMatch) {
		source.output().send(MessageBuilder.withPayload(kafkaMatchHasWinnerMapper.mapMatchHasWinner(tennisMatch)).build());
	}

}

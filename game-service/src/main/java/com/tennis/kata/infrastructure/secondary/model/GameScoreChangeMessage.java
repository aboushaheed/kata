package com.tennis.kata.infrastructure.secondary.model;

public class GameScoreChangeMessage extends GameMessage {
	public static final String TYPE = "com.tennis.kata.game.scoreChange";

	public GameScoreChangeMessage() {
		super(TYPE);
	}

}

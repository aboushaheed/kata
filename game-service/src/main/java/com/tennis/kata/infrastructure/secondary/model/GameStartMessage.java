package com.tennis.kata.infrastructure.secondary.model;

public class GameStartMessage extends GameMessage {
	public static final String TYPE = "com.tennis.kata.game.start";

	public GameStartMessage() {
		super(TYPE);
	}
}

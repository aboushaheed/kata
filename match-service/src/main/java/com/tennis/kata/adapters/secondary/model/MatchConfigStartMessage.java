package com.tennis.kata.adapters.secondary.model;


public class MatchConfigStartMessage extends MatchConfigMessage {
	public static final String TYPE = "com.tennis.kata.game.start";

	public MatchConfigStartMessage() {
		super(TYPE);
	}
}

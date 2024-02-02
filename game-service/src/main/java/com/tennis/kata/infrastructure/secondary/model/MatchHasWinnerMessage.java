package com.tennis.kata.infrastructure.secondary.model;

public class MatchHasWinnerMessage extends MatchWinnerMessage {
	public static final String TYPE = "com.tennis.kata.match.hasWinner";

	public MatchHasWinnerMessage() {
		super(TYPE);
	}
}

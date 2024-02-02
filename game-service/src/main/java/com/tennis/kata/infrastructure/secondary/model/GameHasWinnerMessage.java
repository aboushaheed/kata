package com.tennis.kata.infrastructure.secondary.model;

import lombok.Data;

@Data
public class GameHasWinnerMessage extends GameWinnerMessage {
	public static final String TYPE = "com.tennis.kata.game.hasWinner";

	public GameHasWinnerMessage() {
		super(TYPE);
	}
}

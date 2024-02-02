package com.tennis.kata.infrastructure.secondary.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class GameWinnerMessage extends MessageEvent {
	private GameData game;

	protected GameWinnerMessage(String type) {
		super(type);
	}

	@Data
	public static class GameData {
		private String matchConfig;
		private String winner;
	}
}

package com.tennis.kata.infrastructure.secondary.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class GameMessage extends MessageEvent {
	private GameData game;

	protected GameMessage(String type) {
		super(type);
	}

	@Data
	public static class GameData {
		private String matchConfig;
		private String winner;
	}
}

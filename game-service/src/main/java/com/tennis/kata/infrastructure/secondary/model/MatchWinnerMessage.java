package com.tennis.kata.infrastructure.secondary.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MatchWinnerMessage extends MessageEvent {
	private MatchData match;

	protected MatchWinnerMessage(String type) {
		super(type);
	}

	@Data
	public static class MatchData {
		private String winner;
		private String matchConfig;
	}

}

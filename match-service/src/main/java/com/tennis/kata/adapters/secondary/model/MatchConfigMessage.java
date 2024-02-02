package com.tennis.kata.adapters.secondary.model;

import com.tennis.kata.adapters.MessageEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class MatchConfigMessage extends MessageEvent {

	private MatchData game;
	protected MatchConfigMessage(String type) {
		super(type);
	}

	@Data
	public static class MatchData {
		private String matchConfig;
		private String winner;
	}
}

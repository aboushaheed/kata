package com.tennis.kata.adapters.secondary.model;

public class ConfigMessage extends MatchConfigMessage {
	public static final String TYPE = "com.tennis.kata.match.config";

	public ConfigMessage() {
		super(TYPE);
	}
}

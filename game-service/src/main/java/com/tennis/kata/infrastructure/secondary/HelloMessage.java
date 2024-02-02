package com.tennis.kata.infrastructure.secondary;


import com.tennis.kata.infrastructure.secondary.model.GameMessage;
import java.util.UUID;


public class HelloMessage  extends GameMessage {
	public static final String TYPE = "com.tennis.kata.hello";

	private UUID traceId;

	public HelloMessage() {
		super(TYPE);
		traceId = getId();
	}

}

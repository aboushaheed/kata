package com.tennis.kata.adapters.primary;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class GameEvent {

	private UUID id;
	private UUID parentId;
	private LocalDateTime date;
	private String type;
}

package com.tennis.kata.adapters.primary.model;

import com.tennis.kata.adapters.primary.GameEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GameHasWinnerEvent extends GameEvent {
	public static final String TYPE = "com.tennis.kata.game.hasWinner";
	private GameDTO game;
	@Data
	public static class GameDTO {
		private String matchConfig;
		private String winner;
	}

}

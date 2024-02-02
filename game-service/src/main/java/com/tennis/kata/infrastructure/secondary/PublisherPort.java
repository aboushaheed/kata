package com.tennis.kata.infrastructure.secondary;

import com.tennis.kata.domaine.game.model.Game;

public interface PublisherPort {

	void publishHello();
	void publishStartGame( Game game);
	void publishGameScoreChange( Game game);
	void publishGameHasWinner( Game game);
	void publishMatchHasWinner(Game tennisMatch);
}

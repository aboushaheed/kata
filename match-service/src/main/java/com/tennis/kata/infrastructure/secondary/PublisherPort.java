package com.tennis.kata.infrastructure.secondary;

import com.tennis.kata.domaine.match.model.CreateMatchRequest;

public interface PublisherPort {

	void publishMatchConfig(CreateMatchRequest createMatchRequest);
}

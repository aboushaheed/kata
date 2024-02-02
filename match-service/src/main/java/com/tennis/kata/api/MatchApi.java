package com.tennis.kata.api;

import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import com.tennis.kata.domaine.match.model.CreateMatchResponse;
import com.tennis.kata.domaine.match.model.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MatchApi {

	private final MatchService matchService;

	@Autowired
	public MatchApi(MatchService matchService) {
		this.matchService = matchService;
	}

	@PostMapping("/matchs")
	public CreateMatchResponse createMatch(@Valid  @RequestBody  CreateMatchRequest createMatchRequest)  {
		return matchService.create(createMatchRequest);
	}
}

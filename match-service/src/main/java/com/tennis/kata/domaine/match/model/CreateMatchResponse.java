package com.tennis.kata.domaine.match.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchResponse {
	private String matchConfig;
	private String winner;
}

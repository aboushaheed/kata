package com.tennis.kata.domaine.match.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchRequest {
	@NotBlank(message = "the match configuration is required")
	private String matchConfig;
}

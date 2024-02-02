package com.tennis.kata.domaine.game;

import com.tennis.kata.domaine.game.model.Game;
import com.tennis.kata.infrastructure.primary.MatchConfigAndStartEvent;
import com.tennis.kata.infrastructure.secondary.PublisherPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameService {
  private final PublisherPort publisherPort;
  private static final String[] SCORE_NAMES = {"0", "15", "30", "40"};
  private static final String PLAYER_A = "Player A";
  private static final String PLAYER_B = "Player B";

  @Autowired
  public GameService(PublisherPort publisherPort) {
    this.publisherPort = publisherPort;
  }

  public void play(MatchConfigAndStartEvent event) {
    Game game = newGame(event);
    log.info("a new game is starting now : {}", game);
    publisherPort.publishStartGame(game);
    applyConfig(game);
  }

  public Game newGame(MatchConfigAndStartEvent event) {
    return Game.builder()
        .winner(event.getGame().getWinner())
        .matchConfig(event.getGame().getMatchConfig())
        .build();
  }
  public void applyConfig(Game game) {
    int pointsA = 0;
    int pointsB = 0;

    for (char point : game.getMatchConfig().toCharArray()) {
      if (point == 'A') {
        pointsA++;
      } else if (point == 'B') {
        pointsB++;
      }

      if (isGameWon(pointsA, pointsB)) {
        game.setWinner((pointsA > pointsB ? PLAYER_A : PLAYER_B) + " wins the game");
        log.info("SCORE : {}", game.getWinner());
        publisherPort.publishGameHasWinner(game);

        return;
      } else {
        game.setWinner(printScore(pointsA, pointsB));
        log.info("SCORE : {}", game.getWinner());
        publisherPort.publishGameScoreChange(game);
      }
    }
  }

  private boolean isGameWon(int pointsA, int pointsB) {
    int diff = Math.abs(pointsA - pointsB);
    return (pointsA >= 4 || pointsB >= 4) && diff >= 2;
  }

  private String printScore(int pointsA, int pointsB) {
    String scoreA = convertToTennisScore(pointsA, pointsB);
    String scoreB = convertToTennisScore(pointsB, pointsA);
    return (PLAYER_A + " : " + scoreA + " / " + PLAYER_B + " : " + scoreB);
  }

  private String convertToTennisScore(int points, int opponentPoints) {
    switch (points) {
      case 0: return "0";
      case 1: return "15";
      case 2: return "30";
      case 3: return "40";
      default:
        if (points == opponentPoints) return "Deuce";
        if (points == opponentPoints + 1) return "Advantage";
        if (opponentPoints == points + 1) return "Advantage";
        return "";
    }
  }

}

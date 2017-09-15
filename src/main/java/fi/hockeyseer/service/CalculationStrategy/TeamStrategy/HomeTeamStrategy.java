package fi.hockeyseer.service.CalculationStrategy.TeamStrategy;


import fi.hockeyseer.service.data.TeamStats;
import fi.hockeyseer.service.CalculationStrategy.WinnerStrategy.GameResultContext;

public class HomeTeamStrategy implements TeamStrategy {


    @Override
    public TeamStats updateStats(TeamStats statsmap, Integer gameWinner, Integer homeScore, Integer awayScore) {

        GameResultContext gameResultContext = new GameResultContext(gameWinner);

        statsmap = gameResultContext.getResultStrategy(statsmap, this, homeScore, awayScore);

        return statsmap;
    }
}

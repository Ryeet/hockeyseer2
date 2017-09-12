package fi.hockeyseer.service.CalculationStrategy.TeamStrategy;


import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.service.CalculationStrategy.WinnerStrategy.GameResultContext;

public class HomeTeamStrategy implements TeamStrategy {


    @Override
    public StatsMap updateStats(StatsMap statsmap, Integer gameWinner, Integer homeScore, Integer awayScore) {

        GameResultContext gameResultContext = new GameResultContext(gameWinner);

        statsmap = gameResultContext.getResultStrategy(statsmap, homeScore, awayScore);
        return statsmap;
    }
}

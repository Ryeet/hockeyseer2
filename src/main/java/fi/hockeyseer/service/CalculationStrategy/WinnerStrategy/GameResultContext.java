package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;

import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementContext;

public class GameResultContext {

    private GameResultStrategy strategy;

    public GameResultContext(Integer winner) {
        if (winner == 1) {
            this.strategy = new HomeTeamWin();
        } else if ( winner == 2) {
            this.strategy = new AwayTeamWin();
        } else {
            this.strategy = new TieResult();
        }
    }

    public GameResultStrategy getStrategy() {
        return strategy;
    }

    public StatsMap getResultStrategy(StatsMap map, Integer homeScore, Integer awayScore) {
        return strategy.getTeamWin(map, homeScore, awayScore);
    }
}

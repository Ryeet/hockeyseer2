package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;

import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.TeamStrategy;
import fi.hockeyseer.service.data.TeamStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameResultContext {

    private static final Logger log = LoggerFactory.getLogger(GameResultContext.class);

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

    public TeamStats getResultStrategy(TeamStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {
        return strategy.getTeamWin(map, teamStrategy, homeScore, awayScore);
    }
}

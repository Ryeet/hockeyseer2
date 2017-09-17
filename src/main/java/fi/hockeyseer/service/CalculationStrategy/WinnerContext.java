package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.TeamStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WinnerContext {

    private static final Logger log = LoggerFactory.getLogger(WinnerContext.class);

    private WinnerStrategy strategy;

    public WinnerContext(Integer winner) {

        if (winner == 1) {
            this.strategy = new HomeTeamWinner();
        } else if ( winner == 2) {
            this.strategy = new VisitorTeamWinner();
        } else {
            this.strategy = new TieResult();
        }
    }

    public WinnerStrategy getStrategy() {
        return strategy;
    }

    public TeamStats getResultStrategy(TeamStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {
        return strategy.getTeamWin(map, teamStrategy, homeScore, awayScore);
    }
}

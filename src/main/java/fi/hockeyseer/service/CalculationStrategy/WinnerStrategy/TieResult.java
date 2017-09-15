package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;

import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.TeamStrategy;
import fi.hockeyseer.service.data.TeamStats;

public class TieResult implements GameResultStrategy {


    public TeamStats getTeamWin(TeamStats teamStats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

       teamStats.increaseTies();
       teamStats.increaseGameCount();
        return teamStats;
    }
}

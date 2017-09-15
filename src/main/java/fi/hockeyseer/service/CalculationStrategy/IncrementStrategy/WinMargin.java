package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.service.data.TeamStats;

public class WinMargin implements IncrementStrategy {

    @Override
    public TeamStats incrementStats(TeamStats teamStats, Integer difference) {

        if (difference == 1) {
            teamStats.increaseOneGoalWins();
        } else if(difference == 2) {
            teamStats.increaseTwoGoalWins();
        } else {
            teamStats.increaseMoreGoalWins();
        }

        teamStats.increaseWins();
        teamStats.increaseGameCount();
        return teamStats;
    }


}

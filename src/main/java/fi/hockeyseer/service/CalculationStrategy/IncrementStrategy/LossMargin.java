package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.service.data.TeamStats;

public class LossMargin implements IncrementStrategy {



    @Override
    public TeamStats incrementStats(TeamStats teamStats, Integer difference) {

        if (difference == 1) {
            teamStats.increaseOneGoalLosses();
        } else if (difference == 2) {
            teamStats.increaseTwoGoalLosses();
        } else {
            teamStats.increaseMoreGoalLosses();
        }

        teamStats.increaseLosses();
        teamStats.increaseGameCount();
        return teamStats;
    }


}

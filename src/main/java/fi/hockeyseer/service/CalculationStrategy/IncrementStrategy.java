package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.TeamStats;

public interface IncrementStrategy {


    TeamStats incrementStats(TeamStats teamStats, Integer difference);
}

class WinMargin implements IncrementStrategy {

    @Override
    public TeamStats incrementStats(TeamStats teamStats, Integer difference) {

        if (difference == 1) {
            teamStats.increaseOneGoalWins();
        } else if (difference == 2) {
            teamStats.increaseTwoGoalWins();
        } else {
            teamStats.increaseMoreGoalWins();
        }

        teamStats.increaseWins();
        teamStats.increaseGameCount();
        return teamStats;
    }


}


class LossMargin implements IncrementStrategy {


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

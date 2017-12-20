package fi.hockeyseer.service.calc.strategy;

import fi.hockeyseer.service.calc.stats.basic.MarginStats;

public interface IncrementStrategy {


    MarginStats incrementStats(MarginStats marginStats, Integer difference);
}

class WinMargin implements IncrementStrategy {

    @Override
    public MarginStats incrementStats(MarginStats marginStats, Integer difference) {

        if (difference == 1) {
            marginStats.increaseOneGoalWins();
        } else if (difference == 2) {
            marginStats.increaseTwoGoalWins();
        } else {
            marginStats.increaseMoreGoalWins();
        }

        marginStats.increaseWins();
        marginStats.increaseGameCount();
        return marginStats;
    }


}


class LossMargin implements IncrementStrategy {


    @Override
    public MarginStats incrementStats(MarginStats marginStats, Integer difference) {

        if (difference == 1) {
            marginStats.increaseOneGoalLosses();
        } else if (difference == 2) {
            marginStats.increaseTwoGoalLosses();
        } else {
            marginStats.increaseMoreGoalLosses();
        }

        marginStats.increaseLosses();
        marginStats.increaseGameCount();
        return marginStats;
    }


}

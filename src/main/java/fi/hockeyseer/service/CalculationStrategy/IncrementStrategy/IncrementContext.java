package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.HomeTeamStrategy;
import fi.hockeyseer.service.CalculationStrategy.WinnerStrategy.GameResultContext;
import fi.hockeyseer.service.CalculationStrategy.WinnerStrategy.GameResultStrategy;
import fi.hockeyseer.service.CalculationStrategy.WinnerStrategy.HomeTeamWin;

public class IncrementContext {

    private IncrementStrategy incrementStrategy;


    public IncrementContext(Integer difference) {
        if (difference > 0) {
            this.incrementStrategy = new WinMargin();
        } else {
            this.incrementStrategy = new LossMargin();
        }
    }

    public StatsMap incrementStats(StatsMap map, Integer difference)
    {
        return incrementStrategy.incrementStats(
                map, difference
        );
    }

}

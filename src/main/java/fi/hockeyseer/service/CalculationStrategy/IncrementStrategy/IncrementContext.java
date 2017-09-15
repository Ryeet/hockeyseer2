package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.service.data.TeamStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementContext {

    private static final Logger log = LoggerFactory.getLogger(IncrementContext.class);
    private IncrementStrategy incrementStrategy;


    public IncrementContext(Boolean team1) {
        log.debug("winner = " + team1);
        if (team1 == true) {
            this.incrementStrategy = new WinMargin();
        } else {
            this.incrementStrategy = new LossMargin();
        }
    }

    public TeamStats incrementStats(TeamStats teamStats, Integer difference)
    {
        return incrementStrategy.incrementStats(teamStats, difference);
    }

}

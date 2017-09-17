package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.TeamStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementContext {

    private static final Logger log = LoggerFactory.getLogger(IncrementContext.class);
    private IncrementStrategy incrementStrategy;


    public IncrementContext(Boolean playingTeamIsWinner) {
        if (playingTeamIsWinner == true) {
            this.incrementStrategy = new WinMargin();
        } else {
            this.incrementStrategy = new LossMargin();
        }
    }

    public TeamStats incrementStats(TeamStats teamStats, Integer difference) {
        return incrementStrategy.incrementStats(teamStats, difference);
    }

}

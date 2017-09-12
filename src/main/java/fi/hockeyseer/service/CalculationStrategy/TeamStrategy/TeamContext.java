package fi.hockeyseer.service.CalculationStrategy.TeamStrategy;

import fi.hockeyseer.domain.Stats;
import fi.hockeyseer.domain.StatsMap;

import java.util.Map;

public class TeamContext {


    private TeamStrategy teamStrategy;

    public void setTeamStrategy(TeamStrategy teamStrategy) {
        this.teamStrategy = teamStrategy;
    }

    public StatsMap updateStats(StatsMap statsmap, Integer gameWinner, Integer homeScore, Integer awayScore) {
        return teamStrategy.updateStats(statsmap, gameWinner, homeScore, awayScore);
    }

}

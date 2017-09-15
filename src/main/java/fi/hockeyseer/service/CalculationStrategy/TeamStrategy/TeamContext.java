package fi.hockeyseer.service.CalculationStrategy.TeamStrategy;

import fi.hockeyseer.service.data.TeamStats;

public class TeamContext {


    private TeamStrategy teamStrategy;

    public void setTeamStrategy(TeamStrategy teamStrategy) {
        this.teamStrategy = teamStrategy;
    }

    public TeamStats updateStats(TeamStats statsmap, Integer gameWinner, Integer homeScore, Integer awayScore) {
        return teamStrategy.updateStats(statsmap, gameWinner, homeScore, awayScore);
    }

}

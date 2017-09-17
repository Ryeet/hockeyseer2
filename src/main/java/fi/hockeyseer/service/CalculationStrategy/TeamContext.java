package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.TeamStats;

public class TeamContext {


    private TeamStrategy teamStrategy;

    public void setTeamStrategy(Boolean teamIsHomeTeam) {
        if (teamIsHomeTeam = true) {
            this.teamStrategy = new HomeTeamStrategy();
        } else {
            this.teamStrategy = new VisitorTeamStrategy();
        }

    }

    public TeamStrategy getTeamStrategy() {
        return teamStrategy;
    }

    public TeamStats updateStats(TeamStats statsmap, Integer gameWinner, Integer homeScore, Integer awayScore) {
        return teamStrategy.updateStats(statsmap, gameWinner, homeScore, awayScore);
    }

}

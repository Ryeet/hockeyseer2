package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.stats.basic.MarginStats;

public class TeamContext {


    private TeamStrategy teamStrategy;

    public void setTeamStrategy(Boolean teamIsHomeTeam) {
        if (teamIsHomeTeam == true) {
            this.teamStrategy = new HomeTeamStrategy();
        } else {
            this.teamStrategy = new VisitorTeamStrategy();
        }

    }

    public MarginStats updateStats(MarginStats marginStats, Integer gameWinner, Integer homeScore, Integer awayScore) {
        return teamStrategy.updateStats(marginStats, gameWinner, homeScore, awayScore);
    }

}

package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;

import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.AwayTeamStrategy;
import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.TeamContext;
import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.TeamStrategy;
import fi.hockeyseer.service.data.TeamStats;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementContext;

public class AwayTeamWin implements GameResultStrategy {


    @Override
    public TeamStats getTeamWin(TeamStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(teamStrategy instanceof AwayTeamStrategy);

        map = context.incrementStats(map, awayScore - homeScore);

        return map;

    }
}

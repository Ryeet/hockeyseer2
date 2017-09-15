package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;


import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.HomeTeamStrategy;
import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.TeamStrategy;
import fi.hockeyseer.service.data.TeamStats;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementContext;

public class HomeTeamWin implements GameResultStrategy {


    @Override
    public TeamStats getTeamWin(TeamStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {


        IncrementContext context = new IncrementContext(teamStrategy instanceof HomeTeamStrategy);

        map = context.incrementStats(map, homeScore - awayScore);

        return map;

    }
}

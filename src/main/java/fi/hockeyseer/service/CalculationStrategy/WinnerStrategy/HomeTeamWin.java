package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;


import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementContext;

public class HomeTeamWin implements GameResultStrategy {


    @Override
    public StatsMap getTeamWin(StatsMap map, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(homeScore - awayScore);

        map = context.incrementStats(map, homeScore - awayScore);

        return map;

    }
}

package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;

import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementContext;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementStrategy;

public class AwayTeamWin implements GameResultStrategy {


    @Override
    public StatsMap getTeamWin(StatsMap map, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(awayScore - homeScore);

        map = context.incrementStats(map, awayScore - homeScore);

        return map;

    }
}

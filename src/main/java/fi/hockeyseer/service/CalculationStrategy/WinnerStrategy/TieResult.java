package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;

import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.service.CalculationStrategy.IncrementStrategy.IncrementStrategy;

public class TieResult implements GameResultStrategy {


    public StatsMap getTeamWin(StatsMap map, Integer homeScore, Integer awayScore) {
        return map;
    }
}

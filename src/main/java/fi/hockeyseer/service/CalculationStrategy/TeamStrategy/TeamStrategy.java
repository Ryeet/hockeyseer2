package fi.hockeyseer.service.CalculationStrategy.TeamStrategy;

import fi.hockeyseer.domain.Stats;
import fi.hockeyseer.domain.StatsMap;

import java.util.Map;

public interface TeamStrategy {

   StatsMap updateStats(StatsMap statsmap, Integer gameWinner, Integer homeScore, Integer awayScore);

}



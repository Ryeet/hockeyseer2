package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.domain.StatsMap;

public interface IncrementStrategy {


    StatsMap incrementStats(StatsMap map, Integer difference);
}

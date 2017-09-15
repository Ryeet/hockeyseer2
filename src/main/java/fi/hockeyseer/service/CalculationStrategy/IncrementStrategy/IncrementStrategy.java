package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.service.data.TeamStats;

public interface IncrementStrategy {


    TeamStats incrementStats(TeamStats teamStats, Integer difference);
}

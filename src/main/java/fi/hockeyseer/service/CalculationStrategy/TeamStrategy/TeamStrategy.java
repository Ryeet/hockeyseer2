package fi.hockeyseer.service.CalculationStrategy.TeamStrategy;

import fi.hockeyseer.service.data.TeamStats;

public interface TeamStrategy {

   TeamStats updateStats(TeamStats statsmap, Integer gameWinner, Integer homeScore, Integer awayScore);

}



package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;


import fi.hockeyseer.domain.StatsMap;

public interface GameResultStrategy {


    StatsMap getTeamWin(StatsMap map, Integer homeScore, Integer awayScore);
}

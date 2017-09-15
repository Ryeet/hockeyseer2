package fi.hockeyseer.service.CalculationStrategy.WinnerStrategy;


import fi.hockeyseer.service.CalculationStrategy.TeamStrategy.TeamStrategy;
import fi.hockeyseer.service.data.TeamStats;

public interface GameResultStrategy {


    TeamStats getTeamWin(TeamStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore);
}

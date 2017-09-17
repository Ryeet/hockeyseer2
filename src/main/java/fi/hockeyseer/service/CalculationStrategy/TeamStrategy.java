package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.TeamStats;

public interface TeamStrategy {

   TeamStats updateStats(TeamStats stats, Integer gameWinner, Integer homeScore, Integer awayScore);

}


class HomeTeamStrategy implements TeamStrategy {


    @Override
    public TeamStats updateStats(TeamStats stats, Integer gameWinner, Integer homeScore, Integer awayScore) {

        WinnerContext gameResultContext = new WinnerContext(gameWinner);

        stats = gameResultContext.getResultStrategy(stats, this, homeScore, awayScore);

        return stats;
    }
}

class VisitorTeamStrategy implements TeamStrategy {


   @Override
   public TeamStats updateStats(TeamStats statsmap, Integer gameWinner, Integer homeScore, Integer awayScore) {

      WinnerContext gameResultContext = new WinnerContext(gameWinner);

      statsmap = gameResultContext.getResultStrategy(statsmap, this, homeScore, awayScore);

      return statsmap;
   }
}

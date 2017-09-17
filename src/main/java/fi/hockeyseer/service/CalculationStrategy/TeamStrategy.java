package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.MarginStats;
import fi.hockeyseer.service.data.MarginStats;

public interface TeamStrategy {

   MarginStats updateStats(MarginStats stats, Integer gameWinner, Integer homeScore, Integer awayScore);

}


class HomeTeamStrategy implements TeamStrategy {


    @Override
    public MarginStats updateStats(MarginStats stats, Integer gameWinner, Integer homeScore, Integer awayScore) {

        WinnerContext gameResultContext = new WinnerContext(gameWinner);

        stats = gameResultContext.getResultStrategy(stats, this, homeScore, awayScore);

        return stats;
    }
}

class VisitorTeamStrategy implements TeamStrategy {


   @Override
   public MarginStats updateStats(MarginStats marginStats, Integer gameWinner, Integer homeScore, Integer awayScore) {

      WinnerContext gameResultContext = new WinnerContext(gameWinner);

      return gameResultContext.getResultStrategy(marginStats, this, homeScore, awayScore);
   }
}

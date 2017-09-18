package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.service.data.stats.basic.MarginStats;

public interface TeamStrategy {

   MarginStats updateStats(MarginStats stats, Integer gameWinner, Integer homeScore, Integer awayScore);

}


class HomeTeamStrategy implements TeamStrategy {


    @Override
    public MarginStats updateStats(MarginStats marginStats, Integer gameWinner, Integer homeScore, Integer awayScore) {

        WinnerContext gameResultContext = new WinnerContext(gameWinner);

        marginStats.increaseGoalsFor(homeScore);
        marginStats.increaseGoalsAgainst(awayScore);

        return gameResultContext.getResultStrategy(marginStats, this, homeScore, awayScore);
    }
}

class VisitorTeamStrategy implements TeamStrategy {


   @Override
   public MarginStats updateStats(MarginStats marginStats, Integer gameWinner, Integer homeScore, Integer awayScore) {

      WinnerContext gameResultContext = new WinnerContext(gameWinner);

      marginStats.increaseGoalsFor(awayScore);
      marginStats.increaseGoalsAgainst(homeScore);

      return gameResultContext.getResultStrategy(marginStats, this, homeScore, awayScore);
   }
}

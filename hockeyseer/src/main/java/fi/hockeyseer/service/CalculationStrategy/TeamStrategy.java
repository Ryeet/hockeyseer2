package fi.hockeyseer.service.CalculationStrategy;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.service.data.stats.basic.MarginStats;

public interface TeamStrategy {

   MarginStats updateStats(MarginStats stats, Integer gameWinner, Integer homeScore, Integer awayScore, Team opponent);

}


class HomeTeamStrategy implements TeamStrategy {


    @Override
    public MarginStats updateStats(MarginStats marginStats, Integer gameWinner, Integer homeScore, Integer awayScore, Team opponent) {

        WinnerContext gameResultContext = new WinnerContext(gameWinner);

        marginStats.increaseGoalsFor(homeScore);
        marginStats.increaseGoalsAgainst(awayScore);
        marginStats.addOpponent(opponent);

        return gameResultContext.getResultStrategy(marginStats, this, homeScore, awayScore);
    }
}

class VisitorTeamStrategy implements TeamStrategy {


   @Override
   public MarginStats updateStats(MarginStats marginStats, Integer gameWinner, Integer homeScore, Integer awayScore, Team opponent) {

      WinnerContext gameResultContext = new WinnerContext(gameWinner);

      marginStats.increaseGoalsFor(awayScore);
      marginStats.increaseGoalsAgainst(homeScore);
      marginStats.addOpponent(opponent);

      return gameResultContext.getResultStrategy(marginStats, this, homeScore, awayScore);
   }
}

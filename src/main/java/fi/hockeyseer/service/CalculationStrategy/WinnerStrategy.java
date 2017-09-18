package fi.hockeyseer.service.CalculationStrategy;


import fi.hockeyseer.service.data.stats.basic.MarginStats;

public interface WinnerStrategy {


    MarginStats getTeamWin(MarginStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore);
}


class HomeTeamWinner implements WinnerStrategy {


    @Override
    public MarginStats getTeamWin(MarginStats stats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(teamStrategy instanceof HomeTeamStrategy);

        return context.incrementStats(stats, homeScore - awayScore);


    }
}

class VisitorTeamWinner implements WinnerStrategy {


    @Override
    public MarginStats getTeamWin(MarginStats stats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(teamStrategy instanceof VisitorTeamStrategy);

        return context.incrementStats(stats, awayScore - homeScore);



    }
}


class TieResult implements WinnerStrategy {


    public MarginStats getTeamWin(MarginStats stats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        stats.increaseTies();
        stats.increaseGameCount();
        return stats;
    }
}

package fi.hockeyseer.service.CalculationStrategy;


import fi.hockeyseer.service.data.TeamStats;

public interface WinnerStrategy {


    TeamStats getTeamWin(TeamStats map, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore);
}


class HomeTeamWinner implements WinnerStrategy {


    @Override
    public TeamStats getTeamWin(TeamStats stats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(teamStrategy instanceof HomeTeamStrategy);

        stats = context.incrementStats(stats, homeScore - awayScore);

        return stats;

    }
}

class VisitorTeamWinner implements WinnerStrategy {


    @Override
    public TeamStats getTeamWin(TeamStats stats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        IncrementContext context = new IncrementContext(teamStrategy instanceof VisitorTeamStrategy);

        stats = context.incrementStats(stats, awayScore - homeScore);

        return stats;

    }
}


class TieResult implements WinnerStrategy {


    public TeamStats getTeamWin(TeamStats stats, TeamStrategy teamStrategy, Integer homeScore, Integer awayScore) {

        stats.increaseTies();
        stats.increaseGameCount();
        return stats;
    }
}

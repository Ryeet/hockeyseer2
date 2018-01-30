package fi.hockeyseer.service.calc;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.LeagueAvgsForOlli;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.LeagueAvgsForOlliRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.calc.stats.basic.MarginStats;
import fi.hockeyseer.service.calc.stats.basic.PercentageStats;
import fi.hockeyseer.service.calc.stats.team.AdjustedPowers;
import fi.hockeyseer.service.calc.stats.team.LeagueAvgStats;
import fi.hockeyseer.service.calc.stats.team.TeamStats;
import fi.hockeyseer.service.calc.stats.team.TendencyStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LickiLicki on 03-Oct-17.
 */
@Service
public class TeamStatsService {


    private static Logger log = LoggerFactory.getLogger(TeamStatsService.class);

    private final GameRepository gameRepository;

    private final TeamRepository teamRepository;

    private final CalculatedStatsService calculatedStatsService;

    private final LeagueAvgsForOlliRepository leagueAvgsForOlliRepository;

    private final String ALL_GAMES = "allGames";
    private final String HOME_GAMES = "homeGames";
    private final String VISITOR_GAMES = "visitorGames";

    @Autowired
    public TeamStatsService(GameRepository gameRepository, TeamRepository teamRepository, CalculatedStatsService calculatedStatsService, LeagueAvgsForOlliRepository leagueAvgsForOlliRepository)
    {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.calculatedStatsService = calculatedStatsService;
        this.leagueAvgsForOlliRepository = leagueAvgsForOlliRepository;
    }

    public List<TeamStats> calculateTeamStatsBase(List<String> seasons) {
        //Basic Tier
        List<TeamStats> teamStatsList = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        LeagueAvgStats allStats = new LeagueAvgStats();
        LeagueAvgStats homeStats = new LeagueAvgStats();
        LeagueAvgStats visitorStats = new LeagueAvgStats();

        teamRepository.findAllByIdLessThan100ByOrderByNameAsc().parallelStream().forEach(team -> {
            executorService.execute(new Runnable() {
                public void run() {
                    List<Game> games = gameRepository.getGamesForTeamByAgainstLeagueWithDate(team.getId(), seasons, true);
                    if (games.size() > 10) {
                        Map<String, MarginStats> stats = calculatedStatsService.calculateWTLandMargins(games, team.getId());
                        TeamStats teamStats = getTeamStats(team, stats);
                        teamStatsList.add(teamStats);

                        allStats.increaseAvgSums(teamStats.getPercentageStatsAll());
                        homeStats.increaseAvgSums(teamStats.getPercentageStatsHome());
                        visitorStats.increaseAvgSums(teamStats.getPercentageStatsVisitor());
                    }
                }
            });
        });
        executorService.shutdown();

        //Tendency Tier
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            Map<String, LeagueAvgStats> leagueAvgStats = new HashMap<String, LeagueAvgStats>();
            leagueAvgStats.put(ALL_GAMES, allStats.setAverages(teamStatsList.size()));
            leagueAvgStats.put(HOME_GAMES, homeStats.setAverages(teamStatsList.size()));
            leagueAvgStats.put(VISITOR_GAMES, visitorStats.setAverages(teamStatsList.size()));

//            saveLeagueHomeAvgStatsToDB(homeStats, date);

            for (TeamStats teamStats : teamStatsList) {
                TendencyStats tendencyStatsAll = new TendencyStats();
                teamStats.setTendencyStatsAll(tendencyStatsAll.setTendencies(teamStats.getPercentageStatsAll(), leagueAvgStats.get(ALL_GAMES)));

                TendencyStats tendencyStatsHome = new TendencyStats();
                teamStats.setTendencyStatsHome(tendencyStatsHome.setTendencies(teamStats.getPercentageStatsHome(), leagueAvgStats.get(HOME_GAMES)));

                TendencyStats tendencyStatsVisitor = new TendencyStats();
                teamStats.setTendencyStatsVisitor(tendencyStatsVisitor.setTendencies(teamStats.getPercentageStatsVisitor(), leagueAvgStats.get(VISITOR_GAMES)));
            }
            return teamStatsList;
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return teamStatsList;
    }

    private void saveLeagueHomeAvgStatsToDB(LeagueAvgStats homeStats, LocalDate date)
    {
        LeagueAvgsForOlli leagueAvgsForOlli = new LeagueAvgsForOlli();
        leagueAvgsForOlli.setDate(date);
        leagueAvgsForOlli.setHomeWinPercentage(homeStats.getAvgWinPercentage());
        leagueAvgsForOlli.setHomeTiePercentage(homeStats.getAvgTiePercentage());
        leagueAvgsForOlli.setHomeLossPercentage(homeStats.getAvgLossPercentage());
        leagueAvgsForOlli.setHomegfa(homeStats.getAvgGoalsFor());
        leagueAvgsForOlli.setHomegaa(homeStats.getAvgGoalsAgainst());

        leagueAvgsForOlliRepository.save(leagueAvgsForOlli);
    }

    private TeamStats getTeamStats(Team team, Map<String, MarginStats> stats)
    {
        TeamStats teamStats = new TeamStats();

        teamStats.setTeam(team);
        teamStats.setMarginStats(stats);
        teamStats.setPercentageStatsAll(new PercentageStats(stats.get("allGames")));
        teamStats.setPercentageStatsHome(new PercentageStats(stats.get("homeGames")));
        teamStats.setPercentageStatsVisitor(new PercentageStats(stats.get("visitorGames")));
        return teamStats;
    }

    public List<TeamStats> calculateFinalTeamStats(List<Team> teams, List<TeamStats> teamStatsList)
    {
        List<TeamStats> finalList = new ArrayList<TeamStats>();
        //Adjusted Powers Tier
        for (TeamStats teamStats :  teamStatsList)
        {
            for (Team team : teams)
            {
                if(teamStats.getTeam().getId() == team.getId())
                {
                    //ALL
                    AdjustedPowers adjustedPowersAll = new AdjustedPowers();
                    BigDecimal opponentWinTendenciesSum = BigDecimal.ZERO;
                    BigDecimal opponentLossTendenciesSum = BigDecimal.ZERO;
                    BigDecimal opponentTieTendenciesSum = BigDecimal.ZERO;
                    BigDecimal opponentScorePowersSum = BigDecimal.ZERO;
                    BigDecimal opponentDefenseSucksSum = BigDecimal.ZERO;
                    BigDecimal opponents = BigDecimal.valueOf(teamStats.getMarginStats().get(ALL_GAMES).getOpponents().size());

                    for (Team opponent : teamStats.getMarginStats().get(ALL_GAMES).getOpponents())
                    {
                        for (TeamStats opponentStats : teamStatsList)
                        {
                            if (opponent.getId() == opponentStats.getTeam().getId())
                            {
                                opponentWinTendenciesSum = opponentWinTendenciesSum.add(opponentStats.getTendencyStatsAll().getWinTendency());
                                opponentLossTendenciesSum = opponentLossTendenciesSum.add(opponentStats.getTendencyStatsAll().getLossTendency());
                                opponentTieTendenciesSum = opponentTieTendenciesSum.add(opponentStats.getTendencyStatsAll().getTieTendency());
                                opponentScorePowersSum = opponentScorePowersSum.add(opponentStats.getTendencyStatsAll().getScorePowerTendency());
                                opponentDefenseSucksSum = opponentDefenseSucksSum.add(opponentStats.getTendencyStatsAll().getDefenseSuckTendency());
                            }
                        }
                    }
                    adjustedPowersAll.setAdjustedWinTendency(teamStats.getTendencyStatsAll().getWinTendency().divide(opponentLossTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersAll.setAdjustedLossTendency(teamStats.getTendencyStatsAll().getLossTendency().divide(opponentWinTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersAll.setAdjustedTieTendency(teamStats.getTendencyStatsAll().getTieTendency().divide(opponentTieTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersAll.setAdjustedScorePower(teamStats.getTendencyStatsAll().getScorePowerTendency().divide(opponentDefenseSucksSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersAll.setAdjustedDefenseSuck(teamStats.getTendencyStatsAll().getDefenseSuckTendency().divide(opponentScorePowersSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    teamStats.setAdjustedPowersAll(adjustedPowersAll);

                    //HOME
                    AdjustedPowers adjustedPowersHome = new AdjustedPowers();
                    opponentWinTendenciesSum = BigDecimal.ZERO;
                    opponentLossTendenciesSum = BigDecimal.ZERO;
                    opponentTieTendenciesSum = BigDecimal.ZERO;
                    opponentScorePowersSum = BigDecimal.ZERO;
                    opponentDefenseSucksSum = BigDecimal.ZERO;
                    opponents = BigDecimal.valueOf(teamStats.getMarginStats().get(HOME_GAMES).getOpponents().size());

                    for (Team opponent : teamStats.getMarginStats().get(HOME_GAMES).getOpponents())
                    {
                        for (TeamStats opponentStats : teamStatsList)
                        {
                            if (opponent.getId() == opponentStats.getTeam().getId())
                            {
                                opponentWinTendenciesSum = opponentWinTendenciesSum.add(opponentStats.getTendencyStatsVisitor().getWinTendency());
                                opponentLossTendenciesSum = opponentLossTendenciesSum.add(opponentStats.getTendencyStatsVisitor().getLossTendency());
                                opponentTieTendenciesSum = opponentTieTendenciesSum.add(opponentStats.getTendencyStatsVisitor().getTieTendency());
                                opponentScorePowersSum = opponentScorePowersSum.add(opponentStats.getTendencyStatsVisitor().getScorePowerTendency());
                                opponentDefenseSucksSum = opponentDefenseSucksSum.add(opponentStats.getTendencyStatsVisitor().getDefenseSuckTendency());
                            }
                        }
                    }
                    adjustedPowersHome.setAdjustedWinTendency(teamStats.getTendencyStatsHome().getWinTendency().divide(opponentLossTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersHome.setAdjustedLossTendency(teamStats.getTendencyStatsHome().getLossTendency().divide(opponentWinTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersHome.setAdjustedTieTendency(teamStats.getTendencyStatsHome().getTieTendency().divide(opponentTieTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersHome.setAdjustedScorePower(teamStats.getTendencyStatsHome().getScorePowerTendency().divide(opponentDefenseSucksSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersHome.setAdjustedDefenseSuck(teamStats.getTendencyStatsHome().getDefenseSuckTendency().divide(opponentScorePowersSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    teamStats.setAdjustedPowersHome(adjustedPowersHome);

                    //VISITOR
                    AdjustedPowers adjustedPowersVisitor = new AdjustedPowers();
                    opponentWinTendenciesSum = BigDecimal.ZERO;
                    opponentLossTendenciesSum = BigDecimal.ZERO;
                    opponentTieTendenciesSum = BigDecimal.ZERO;
                    opponentScorePowersSum = BigDecimal.ZERO;
                    opponentDefenseSucksSum = BigDecimal.ZERO;
                    opponents = BigDecimal.valueOf(teamStats.getMarginStats().get(VISITOR_GAMES).getOpponents().size());

                    for (Team opponent : teamStats.getMarginStats().get(VISITOR_GAMES).getOpponents())
                    {
                        for (TeamStats opponentStats : teamStatsList)
                        {
                            if (opponent.getId() == opponentStats.getTeam().getId())
                            {
                                opponentWinTendenciesSum = opponentWinTendenciesSum.add(opponentStats.getTendencyStatsHome().getWinTendency());
                                opponentLossTendenciesSum = opponentLossTendenciesSum.add(opponentStats.getTendencyStatsHome().getLossTendency());
                                opponentTieTendenciesSum = opponentTieTendenciesSum.add(opponentStats.getTendencyStatsHome().getTieTendency());
                                opponentScorePowersSum = opponentScorePowersSum.add(opponentStats.getTendencyStatsHome().getScorePowerTendency());
                                opponentDefenseSucksSum = opponentDefenseSucksSum.add(opponentStats.getTendencyStatsHome().getDefenseSuckTendency());
                            }
                        }
                    }
                    adjustedPowersVisitor.setAdjustedWinTendency(teamStats.getTendencyStatsVisitor().getWinTendency().divide(opponentLossTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersVisitor.setAdjustedLossTendency(teamStats.getTendencyStatsVisitor().getLossTendency().divide(opponentWinTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersVisitor.setAdjustedTieTendency(teamStats.getTendencyStatsVisitor().getTieTendency().divide(opponentTieTendenciesSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersVisitor.setAdjustedScorePower(teamStats.getTendencyStatsVisitor().getScorePowerTendency().divide(opponentDefenseSucksSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    adjustedPowersVisitor.setAdjustedDefenseSuck(teamStats.getTendencyStatsVisitor().getDefenseSuckTendency().divide(opponentScorePowersSum.divide(opponents, MathContext.DECIMAL32), MathContext.DECIMAL32));
                    teamStats.setAdjustedPowersVisitor(adjustedPowersVisitor);

                    finalList.add(teamStats);
                }
            }
        }


        return finalList;
    }
}


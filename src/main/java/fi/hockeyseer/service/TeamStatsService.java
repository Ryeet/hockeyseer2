package fi.hockeyseer.service;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.data.stats.basic.MarginStats;
import fi.hockeyseer.service.data.stats.basic.PercentageStats;
import fi.hockeyseer.service.data.stats.team.AdjustedPowers;
import fi.hockeyseer.service.data.stats.team.LeagueAvgStats;
import fi.hockeyseer.service.data.stats.team.TeamStats;
import fi.hockeyseer.service.data.stats.team.TendencyStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by LickiLicki on 03-Oct-17.
 */
@Service
public class TeamStatsService {


    private static Logger log = LoggerFactory.getLogger(TeamStatsService.class);

    private final GameRepository gameRepository;

    private final TeamRepository teamRepository;

    private final CalculatedStatsService calculatedStatsService;

    private final String ALL_GAMES = "allGames";
    private final String HOME_GAMES = "homeGames";
    private final String VISITOR_GAMES = "visitorGames";

    @Autowired
    public TeamStatsService(GameRepository gameRepository, TeamRepository teamRepository, CalculatedStatsService calculatedStatsService)
    {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.calculatedStatsService = calculatedStatsService;
    }

    public void generateAllStats(List<Team> teams, List<String> seasons)
    {
        //Basic Tier
        List<TeamStats> teamStatsList = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        LeagueAvgStats allStats = new LeagueAvgStats();
        LeagueAvgStats homeStats = new LeagueAvgStats();
        LeagueAvgStats visitorStats = new LeagueAvgStats();

        teamRepository.findAllByIdLessThan100ByOrderByNameAsc().parallelStream().forEach(team -> {
            executorService.execute(new Runnable() {
                public void run() {
                    List<Game> games = gameRepository.getGamesForTeamByAgainstLeague(team.getId(), seasons, true);
                    if (games.size() > 10) {
                        Map<String, MarginStats> stats = calculatedStatsService.calculateWTLandMargins(games, team.getId());
                        log.debug(stats.toString());
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
        try
        {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            Map<String, LeagueAvgStats> leagueAvgStats = new HashMap<String, LeagueAvgStats>();
            leagueAvgStats.put(ALL_GAMES, allStats.setAverages(teamStatsList.size()));
            leagueAvgStats.put(HOME_GAMES, homeStats.setAverages(teamStatsList.size()));
            leagueAvgStats.put(VISITOR_GAMES, visitorStats.setAverages(teamStatsList.size()));

            log.debug(leagueAvgStats.get(HOME_GAMES).toString());

            for (TeamStats teamStats : teamStatsList)
            {
                TendencyStats tendencyStatsAll = new TendencyStats();
                teamStats.setTendencyStatsAll(tendencyStatsAll.setTendencies(teamStats.getPercentageStatsAll(),leagueAvgStats.get(ALL_GAMES)));

                TendencyStats tendencyStatsHome = new TendencyStats();
                teamStats.setTendencyStatsHome(tendencyStatsHome.setTendencies(teamStats.getPercentageStatsHome(),leagueAvgStats.get(HOME_GAMES)));

                TendencyStats tendencyStatsVisitor = new TendencyStats();
                teamStats.setTendencyStatsVisitor(tendencyStatsVisitor.setTendencies(teamStats.getPercentageStatsVisitor(),leagueAvgStats.get(VISITOR_GAMES)));
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //Adjusted Powers Tier
        for (TeamStats teamStats :  teamStatsList)
        {
            for (Team team : teams)
            {
                if(teamStats.getTeam().getId() == team.getId())
                {
                    AdjustedPowers adjustedPowersAll = new AdjustedPowers();
                    BigDecimal test;
                    test = teamStatsList
                            .stream()
                            .filter(ts -> teamStats
                                    .getMarginStats()
                                    .get(ALL_GAMES)
                                    .getOpponents()
                                    .stream()
                                    .anyMatch(t -> t.getId()
                                            .equals(ts.getTeam().getId())))
                            .map(TeamStats::getTendencyStatsAll)
                            .map(TendencyStats::getWinTendency)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);


                    log.debug("ASDGSDAFSDAFSDFASDFASDFASDFAFDSASDF::::::" + test);
                    teamStats.getMarginStats().get(ALL_GAMES).getOpponents();
                    log.debug(teamStats.toString());
                }
            }
        }
    }

    private TeamStats getTeamStats(Team team, Map<String, MarginStats> stats) {
        TeamStats teamStats = new TeamStats();

        teamStats.setTeam(team);
        teamStats.setMarginStats(stats);
        teamStats.setPercentageStatsAll(new PercentageStats(stats.get("allGames")));
        teamStats.setPercentageStatsHome(new PercentageStats(stats.get("homeGames")));
        teamStats.setPercentageStatsVisitor(new PercentageStats(stats.get("visitorGames")));
        return teamStats;
    }
}


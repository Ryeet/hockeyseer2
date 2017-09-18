package fi.hockeyseer.service;

import fi.hockeyseer.domain.*;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.CalculationStrategy.TeamContext;
import fi.hockeyseer.service.data.stats.basic.MarginStats;
import fi.hockeyseer.service.data.stats.basic.PercentageStats;
import fi.hockeyseer.service.data.stats.team.TeamStats;
import fi.hockeyseer.web.forms.SearchToolForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LickiLicki on 31-Aug-17.
 */
@Service
public class CalculatedStatsService {


    private static Logger log = LoggerFactory.getLogger(SeasonService.class);

    private final SeasonService seasonService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    private final String ALL_GAMES = "allGames";
    private final String HOME_GAMES = "homeGames";
    private final String VISITOR_GAMES = "visitorGames";
    private final String GAME_COUNT = "gameCount";
    private final String WIN = "win";
    private final String TIE = "tie";
    private final String LOSS = "loss";
    private final String WIN_MARGIN_1 = "winMargin1";
    private final String WIN_MARGIN_2 = "winMargin2";
    private final String WIN_MARGIN_MORE = "winMarginMore";
    private final String LOSS_MARGIN_1 = "lossMargin1";
    private final String LOSS_MARGIN_2 = "lossMargin2";
    private final String LOSS_MARGIN_MORE = "lossMarginMore";
    private final String RESULTS = "results";


    @Autowired
    public CalculatedStatsService(SeasonService seasonService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository) {
        this.seasonService = seasonService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }

    public List<Game> resolveSearchedGames(SearchToolForm searchToolForm) {
        long againstSelect = searchToolForm.getAgainstSelect();
        Long selectedTeam = searchToolForm.getTeam();

        switch ((int) againstSelect) {
            case 1:
                return gameRepository.getGamesForTeamByAgainstTeam(selectedTeam, searchToolForm.getAgainstTeam(), searchToolForm.getSeason(), true);
            case 2:
                return gameRepository.getGamesForTeamByAgainstDivision(selectedTeam, searchToolForm.getAgainstDivision(), searchToolForm.getSeason(), true);
            case 3:
                return gameRepository.getGamesForTeamByAgainstConference(selectedTeam, searchToolForm.getAgainstConference(), searchToolForm.getSeason(), true);
            case 4:
                return gameRepository.getGamesForTeamByAgainstLeague(selectedTeam, searchToolForm.getSeason(), true);
        }
        return null;
    }

    public void generateAllStats() {
    List<TeamStats> teamStatsList = new ArrayList<>();
        long startTime = System.nanoTime();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        teamRepository.findAll().parallelStream().forEach(team -> {
            executorService.execute(new Runnable() {
                public void run() {
                    List<Game> games = gameRepository.getGamesForTeamByAgainstLeague(team.getId(), Arrays.asList("20162017"), true);
                    if (games.size() > 10) {
                        Map<String, MarginStats> stats = calculateWTLandMargins(games, team.getId());
                        log.debug(stats.toString());
                        TeamStats teamStats = getTeamStats(team, stats);
                        teamStatsList.add(teamStats);
                    }
                }
            });
        });
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            long elapsedTime = System.nanoTime() - startTime;

            BigDecimal sum = teamStatsList
                    .stream()
                    .map(TeamStats::getPercentageStats)
                    .map(PercentageStats::getWinPercentage)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            log.debug("avg win% = " + String.valueOf(sum.divide(new BigDecimal(teamStatsList.size()), MathContext.DECIMAL32)));
            log.debug("Games ending.." + ((double) elapsedTime / 1000000000.0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public TeamStats getTeamStats(Team team, Map<String, MarginStats> stats) {
        TeamStats teamStats = new TeamStats();

        teamStats.setMarginStats(stats);
        teamStats.setPercentageStats(new PercentageStats(stats.get("homeGames")));

        log.debug(team.getName() + " - " + teamStats.getPercentageStats().getWinPercentage());
        return teamStats;
    }

    public Map<String, MarginStats> calculateWTLandMargins(List<Game> games, Long teamId) {
        Map<String, MarginStats> calculatedStats = new HashMap<String, MarginStats>();

        MarginStats homeGameStats = new MarginStats();
        MarginStats visitorGameStats = new MarginStats();


        games.forEach(game -> {
//d
            TeamContext teamContext = new TeamContext();

            teamContext.setTeamStrategy(game.getHomeTeam().getId() == teamId);
            if (game.getHomeTeam().getId() == teamId) {
                teamContext.updateStats(homeGameStats, game.getWinner(), game.getResult().getHome_total(), game.getResult().getVisitor_total());
            } else {
                teamContext.updateStats(visitorGameStats, game.getWinner(),  game.getResult().getHome_total(), game.getResult().getVisitor_total());
            }
        });

        MarginStats allStats = MarginStats.getAllStats(homeGameStats, visitorGameStats);

        calculatedStats.put("allGames", allStats);
        calculatedStats.put("homeGames", homeGameStats);
        calculatedStats.put("visitorGames", visitorGameStats);
        return calculatedStats;
    }



    public ResultsMap countResultDistiribution(List<Game> games, Long team) {
        ResultsMap results = new ResultsMap();

        games.stream().forEach(game ->
        {
            boolean validResult = (game.getResult().getHome_total() <= 6) && (game.getResult().getVisitor_total() <= 6);
            if (game.getHomeTeam().getId() == team) {
                switch (game.getWinner()) {
                    case 1:
                        if (validResult)
                            results.setResults(increaseValue(results.getResults(), "result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "team"));
                        else {
                            results.setResults(increaseValue(results.getResults(), "resultElseTeam"));
                        }
                        break;
                    case 2:
                        if (validResult)
                            results.setResults(increaseValue(results.getResults(), "result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "against"));
                        else {
                            results.setResults(increaseValue(results.getResults(), "resultElseAgainst"));
                        }
                        break;
                    default:
                        if (validResult)
                            results.setResults(increaseValue(results.getResults(), "result" + game.getResult().getHome_total() + game.getResult().getVisitor_total()));
                        else {
                            results.setResults(increaseValue(results.getResults(), "resultElseTie"));
                        }
                        break;
                }
            } else {
                switch (game.getWinner()) {
                    case 1:
                        if (validResult)
                            results.setResults(increaseValue(results.getResults(), "result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "against"));
                        else {
                            results.setResults(increaseValue(results.getResults(), "resultElseAgainst"));
                        }
                        break;
                    case 2:
                        if (validResult)
                            results.setResults(increaseValue(results.getResults(), "result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "team"));
                        else {
                            results.setResults(increaseValue(results.getResults(), "resultElseTeam"));
                        }
                        break;
                    default:
                        if (validResult)
                            results.setResults(increaseValue(results.getResults(), "result" + game.getResult().getHome_total() + game.getResult().getVisitor_total()));
                        else {
                            results.setResults(increaseValue(results.getResults(), "resultElseTie"));
                        }
                        break;
                }
            }
        });
        return results;
    }

    private Map<String, Long> increaseValue(Map<String, Long> map, String key) {
        Long value = map.get(key);
        value = value + 1;
        map.replace(key, value);
        return map;
    }
}

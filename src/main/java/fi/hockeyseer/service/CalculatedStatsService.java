package fi.hockeyseer.service;

import fi.hockeyseer.domain.StatsMap;
import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.web.forms.SearchToolForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final String ALL_GAMES          = "allGames";
    private final String HOME_GAMES         = "homeGames";
    private final String VISITOR_GAMES      = "visitorGames";
    private final String GAME_COUNT         = "gameCount";
    private final String WIN                = "win";
    private final String TIE                = "tie";
    private final String LOSS               = "loss";
    private final String WIN_MARGIN_1       = "winMargin1";
    private final String WIN_MARGIN_2       = "winMargin2";
    private final String WIN_MARGIN_MORE    = "winMarginMore";
    private final String LOSS_MARGIN_1      = "lossMargin1";
    private final String LOSS_MARGIN_2      = "lossMargin2";
    private final String LOSS_MARGIN_MORE   = "lossMarginMore";

    @Autowired
    public CalculatedStatsService(SeasonService seasonService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
    {
        this.seasonService = seasonService;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.resultService = resultService;
    }

    public List<Game> resolveSearchedGames(SearchToolForm searchToolForm)
    {
        long againstSelect = searchToolForm.getAgainstSelect();
        switch ((int) againstSelect)
        {
            case 1:
                return gameRepository.getGamesForTeamByAgainstTeam(searchToolForm.getTeam(), searchToolForm.getAgainstTeam(), searchToolForm.getSeason(),true);
            case 2:
                return gameRepository.getGamesForTeamByAgainstDivision(searchToolForm.getTeam(), searchToolForm.getAgainstDivision(), searchToolForm.getSeason(),true);
            case 3:
                return gameRepository.getGamesForTeamByAgainstConference(searchToolForm.getTeam(), searchToolForm.getAgainstConference(), searchToolForm.getSeason(),true);
            case 4:
                return gameRepository.getGamesForTeamByAgainstLeague(searchToolForm.getTeam(), searchToolForm.getSeason(),true);
        }
        return null;
    }

    private StatsMap increaseValue(StatsMap stats, String key)
    {
        Long value = stats.getBasicStats().get(key);
        value = value + 1;
        stats.getBasicStats().replace(key,value);
        return stats;
    }

    public Map<String, StatsMap> calculateWTLandMargins(List<Game> games, Long team)
    {
        Map<String, StatsMap> calculatedStats = new HashMap<String, StatsMap>();
        calculatedStats.put(ALL_GAMES, new StatsMap());
        calculatedStats.put(HOME_GAMES, new StatsMap());
        calculatedStats.put(VISITOR_GAMES, new StatsMap());

        games.stream().forEach(game ->
        {
            calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES),GAME_COUNT));

            if (game.getHomeTeam().getId() == team)
            {
                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), GAME_COUNT));

                switch (game.getWinner())
                {
                    case 1:
                        calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN));
                        calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), WIN));

                        switch (game.getResult().getHome_total() - game.getResult().getVisitor_total())
                        {
                            case 1:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN_MARGIN_1));
                                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), WIN_MARGIN_1));
                                break;
                            case 2:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN_MARGIN_2));
                                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), WIN_MARGIN_2));
                                break;
                            default:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN_MARGIN_MORE));
                                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), WIN_MARGIN_MORE));
                                break;
                        }
                        break;

                    case 2:
                        calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS));
                        calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), LOSS));

                        switch (game.getResult().getVisitor_total() - game.getResult().getHome_total())
                        {
                            case 1:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS_MARGIN_1));
                                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), LOSS_MARGIN_1));
                                break;
                            case 2:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS_MARGIN_2));
                                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), LOSS_MARGIN_2));
                                break;
                            default:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS_MARGIN_MORE));
                                calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), LOSS_MARGIN_MORE));
                                break;
                        }
                        break;

                    default:
                        calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), TIE));
                        calculatedStats.replace(HOME_GAMES, increaseValue(calculatedStats.get(HOME_GAMES), TIE));
                        break;
                }
            }
            else
            {
                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), GAME_COUNT));

                switch (game.getWinner())
                {
                    case 1:
                        calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS));
                        calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), LOSS));

                        switch (game.getResult().getHome_total() - game.getResult().getVisitor_total())
                        {
                            case 1:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS_MARGIN_1));
                                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), LOSS_MARGIN_1));
                                break;
                            case 2:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS_MARGIN_2));
                                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), LOSS_MARGIN_2));
                                break;
                            default:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), LOSS_MARGIN_MORE));
                                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), LOSS_MARGIN_MORE));
                                break;
                        }
                        break;

                    case 2:
                        calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN));
                        calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), WIN));

                        switch (game.getResult().getVisitor_total() - game.getResult().getHome_total())
                        {
                            case 1:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN_MARGIN_1));
                                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), WIN_MARGIN_1));
                                break;
                            case 2:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN_MARGIN_2));
                                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), WIN_MARGIN_2));
                                break;
                            default:
                                calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), WIN_MARGIN_MORE));
                                calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), WIN_MARGIN_MORE));
                                break;
                        }
                        break;

                    default:
                        calculatedStats.replace(ALL_GAMES, increaseValue(calculatedStats.get(ALL_GAMES), TIE));
                        calculatedStats.replace(VISITOR_GAMES, increaseValue(calculatedStats.get(VISITOR_GAMES), TIE));
                        break;
                }
            }
        });
        return calculatedStats;
    }

    public Map<String, Long> countResults(List<Game> games, Long team)
    {
        Map<String, Long> results = new HashMap<String, Long>();

        games.stream().forEach(game ->
        {
            if (game.getHomeTeam().getId() == team)
            {
                if (game.getWinner() == 1)
                {
                    if (results.containsKey("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "team") == false)
                        results.put("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "team", 1L);
                    else
                    {
                        Long resultCount = results.get("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "team");
                        results.replace("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "team", resultCount + 1);
                    }
                }
                else if (game.getWinner() == 2)
                {
                    if (results.containsKey("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "against") == false)
                        results.put("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "against", 1L);
                    else
                    {
                        Long resultCount = results.get("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "against");
                        results.replace("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "against", resultCount + 1);
                    }
                }
                else
                {
                    if (results.containsKey("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total()) == false)
                        results.put("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total(), 1L);
                    else
                    {
                        Long resultCount = results.get("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total());
                        results.replace("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total(), resultCount + 1);
                    }
                }
            }
            else
            {
                if (game.getWinner() == 1)
                {
                    if (results.containsKey("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "against") == false)
                        results.put("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "against", 1L);
                    else
                    {
                        Long resultCount = results.get("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "against");
                        results.replace("result" + game.getResult().getHome_total() + game.getResult().getVisitor_total() + "against", resultCount + 1);
                    }
                }
                else if (game.getWinner() == 2)
                {
                    if (results.containsKey("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "team") == false)
                        results.put("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "team", 1L);
                    else
                    {
                        Long resultCount = results.get("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "team");
                        results.replace("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total() + "team", resultCount + 1);
                    }
                }
                else
                {
                    if (results.containsKey("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total()) == false)
                        results.put("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total(), 1L);
                    else
                    {
                        Long resultCount = results.get("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total());
                        results.replace("result" + game.getResult().getVisitor_total() + game.getResult().getHome_total(), resultCount + 1);
                    }
                }
            }
        });
        return results;
    }
}

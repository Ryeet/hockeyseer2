package fi.hockeyseer.service;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.SearchToolStats;
import fi.hockeyseer.domain.Team;
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
import java.util.stream.Collectors;

/**
 * Created by LickiLicki on 31-Aug-17.
 */
@Service
public class SearchToolService {


    private static Logger log = LoggerFactory.getLogger(SeasonService.class);

    private final SeasonService seasonService;

    private final GameRepository gameRepository;

    private final ResultService resultService;

    private final TeamRepository teamRepository;

    @Autowired
    public SearchToolService(SeasonService seasonService, GameRepository gameRepository, ResultService resultService, TeamRepository teamRepository)
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
                return filterOutNotPlayedGames(gameRepository.getGamesForTeamByAgainstTeam(searchToolForm.getTeam(), searchToolForm.getAgainstTeam(), searchToolForm.getSeason()));
            case 2:
                return filterOutNotPlayedGames(gameRepository.getGamesForTeamByAgainstDivision(searchToolForm.getTeam(), searchToolForm.getAgainstDivision(), searchToolForm.getSeason()));
            case 3:
                return filterOutNotPlayedGames(gameRepository.getGamesForTeamByAgainstConference(searchToolForm.getTeam(), searchToolForm.getAgainstConference(), searchToolForm.getSeason()));
            case 4:
                return filterOutNotPlayedGames(gameRepository.getGamesForTeamByAgainstLeague(searchToolForm.getTeam(), searchToolForm.getSeason()));
        }
        return null;
    }

    public Map<String,SearchToolStats> resolveSearchToolStats(List<Game> games, Long team)
    {
        Map<String, SearchToolStats> searchToolStats = new HashMap<String, SearchToolStats>();
        searchToolStats.put("allGames", new SearchToolStats());
        searchToolStats.put("homeGames", new SearchToolStats());
        searchToolStats.put("visitorGames", new SearchToolStats());

        games.stream().forEach(game ->
        {
            searchToolStats.get("allGames").addGameCount();

            if (game.getHomeTeam().getId() == team)
            {
                searchToolStats.get("homeGames").addGameCount();

                if (game.getWinner() == 1)
                {
                    searchToolStats.get("allGames").addWin();
                    searchToolStats.get("homeGames").addWin();
                    if ((game.getResult().getHome_total() - game.getResult().getVisitor_total()) == 1)
                    {
                        searchToolStats.get("allGames").addWinMargin1();
                        searchToolStats.get("homeGames").addWinMargin1();
                    }
                    else if ((game.getResult().getHome_total() - game.getResult().getVisitor_total()) == 2)
                    {
                        searchToolStats.get("allGames").addWinMargin2();
                        searchToolStats.get("homeGames").addWinMargin2();
                    }
                    else
                    {
                        searchToolStats.get("allGames").addWinMarginMore();
                        searchToolStats.get("homeGames").addWinMarginMore();
                    }
                }
                else if (game.getWinner() == 2)
                {
                    searchToolStats.get("allGames").addLoss();
                    searchToolStats.get("homeGames").addLoss();
                    if ((game.getResult().getVisitor_total() - game.getResult().getHome_total()) == 1)
                    {
                        searchToolStats.get("allGames").addLossMargin1();
                        searchToolStats.get("homeGames").addLossMargin1();
                    }
                    else if ((game.getResult().getVisitor_total() - game.getResult().getHome_total()) == 2)
                    {
                        searchToolStats.get("allGames").addLossMargin2();
                        searchToolStats.get("homeGames").addLossMargin2();
                    }
                    else
                    {
                        searchToolStats.get("allGames").addLossMarginMore();
                        searchToolStats.get("homeGames").addLossMarginMore();
                    }
                }
                else
                {
                    searchToolStats.get("allGames").addTie();
                    searchToolStats.get("homeGames").addTie();
                }
            }
            else
            {
                searchToolStats.get("visitorGames").addGameCount();

                if (game.getWinner() == 2)
                {
                    searchToolStats.get("allGames").addWin();
                    searchToolStats.get("visitorGames").addWin();
                    if ((game.getResult().getVisitor_total() - game.getResult().getHome_total()) == 1)
                    {
                        searchToolStats.get("allGames").addWinMargin1();
                        searchToolStats.get("visitorGames").addWinMargin1();
                    }
                    else if ((game.getResult().getVisitor_total() - game.getResult().getHome_total()) == 2)
                    {
                        searchToolStats.get("allGames").addWinMargin2();
                        searchToolStats.get("visitorGames").addWinMargin2();
                    }
                    else
                    {
                        searchToolStats.get("allGames").addWinMarginMore();
                        searchToolStats.get("visitorGames").addWinMarginMore();
                    }
                }
                else if (game.getWinner() == 1)
                {
                    searchToolStats.get("allGames").addLoss();
                    searchToolStats.get("visitorGames").addLoss();
                    if ((game.getResult().getHome_total() - game.getResult().getVisitor_total()) == 1)
                    {
                        searchToolStats.get("allGames").addLossMargin1();
                        searchToolStats.get("visitorGames").addLossMargin1();
                    }
                    else if ((game.getResult().getHome_total() - game.getResult().getVisitor_total()) == 2)
                    {
                        searchToolStats.get("allGames").addLossMargin2();
                        searchToolStats.get("visitorGames").addLossMargin2();
                    }
                    else
                    {
                        searchToolStats.get("allGames").addLossMarginMore();
                        searchToolStats.get("visitorGames").addLossMarginMore();
                    }
                }
                else
                {
                    searchToolStats.get("allGames").addTie();
                    searchToolStats.get("visitorGames").addTie();
                }
            }
        });
        return searchToolStats;
    }

    private List<Game> filterOutNotPlayedGames(List<Game> games)
    {
        return games.stream().filter(game -> game.getPlayed() != false).collect(Collectors.toList());
    }


}

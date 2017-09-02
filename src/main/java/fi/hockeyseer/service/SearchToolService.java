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

        searchToolStats.put("allGames",resolveWTL(games, team, "allGames"));

        return searchToolStats;
    }

    private List<Game> filterOutNotPlayedGames(List<Game> games)
    {
        return games.stream().filter(game -> game.getPlayed() != false).collect(Collectors.toList());
    }

    private SearchToolStats resolveWTL(List<Game> games, Long team, String type)
    {
        SearchToolStats stats = new SearchToolStats();

        if("allGames".equals(type))
        {
            stats.setWin(games.stream().filter(game -> (game.getHomeTeam().getId() == team && game.getWinner() == 1) || (game.getVisitorTeam().getId() == team && game.getWinner() == 2)).count());
            stats.setTie(games.stream().filter(game -> (game.getWinner() == 0)).count());
            stats.setLoss(games.stream().filter(game -> (game.getHomeTeam().getId() == team && game.getWinner() == 2) || (game.getVisitorTeam().getId() == team && game.getWinner() == 1)).count());
        }

        return stats;
    }

}

package fi.hockeyseer.service;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.web.forms.SearchToolForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                return gameRepository.getGamesForTeamByAgainstTeam(searchToolForm.getTeam(), searchToolForm.getAgainstTeam());
            case 2:
                return gameRepository.getGamesForTeamByAgainstDivision(searchToolForm.getTeam(), searchToolForm.getAgainstDivision());
            case 3:
                return gameRepository.getGamesForTeamByAgainstConference(searchToolForm.getTeam(), searchToolForm.getAgainstConference());
            case 4:
                return gameRepository.getGamesForTeamByAgainstLeague(searchToolForm.getTeam());
        }
        return null;
    }

    public List<Game> resolveWinner(SearchToolForm searchToolForm)
    {
//        long againstSelect = searchToolForm.getAgainstSelect();
//        switch ((int) againstSelect)
//        {
//            case 1:
//                return gameRepository.getGamesForTeamByAgainstTeam(searchToolForm.getTeam(), searchToolForm.getAgainstTeam());
//            case 2:
//                return gameRepository.getGamesForTeamByAgainstDivision(searchToolForm.getTeam(), searchToolForm.getAgainstDivision());
//            case 3:
//                return gameRepository.getGamesForTeamByAgainstConference(searchToolForm.getTeam(), searchToolForm.getAgainstConference());
//            case 4:
//                return gameRepository.getGamesForTeamByAgainstLeague(searchToolForm.getTeam());
//        }
        return null;
    }

}

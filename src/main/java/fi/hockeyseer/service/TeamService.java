package fi.hockeyseer.service;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.domain.enumeration.Conference;
import fi.hockeyseer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alekstu on 31.5.2017.
 */
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    List<Team> findTeamsByConference(Conference conference) {
        return teamRepository.findByConference(conference);
    }

}

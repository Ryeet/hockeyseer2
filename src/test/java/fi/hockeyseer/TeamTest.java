package fi.hockeyseer;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.domain.enumeration.Conference;
import fi.hockeyseer.domain.enumeration.Division;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alekstu on 31.5.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HockeyseerApplication.class)
public class TeamTest {


    private static final String START_NAME = "Boston Bruins";
    private static final String END_NAME = "Buffalo Sabres";

    private static final Conference START_CONF = Conference.Eastern;
    private static final Conference END_CONF = Conference.Western;

    private static final Division START_DIV = Division.Atlantic;
    private static final Division END_DIV = Division.Central;


    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntityManager em;

    private Team team;


    public static Team createTeam(EntityManager em) {
        return new Team().name(START_NAME).conference(START_CONF).division(START_DIV);

    }


    @Before
    public void initTest() {
          team = createTeam(em);
    }


    @Test
    @Transactional
    public void testTeam() {
        int databaseSizeBeforeCreate = teamRepository.findAll().size();

        Team savedTeam = teamRepository.save(team);

        List<Team> teams = teamRepository.findAll();
        assertThat(teams).hasSize(databaseSizeBeforeCreate + 1);
        assertThat(savedTeam.getName()).isEqualToIgnoringCase(START_NAME);
        assertThat(savedTeam.getDivision()).isEqualTo(START_DIV);
        assertThat(savedTeam.getConference()).isEqualTo(START_CONF);

    }


    @Test
    @Transactional
    public void deleteTeam() throws Exception {
        Team savedTeam = teamRepository.saveAndFlush(team);
        int dataBaseSizeBeforeDelete = teamRepository.findAll().size();

        teamRepository.delete(savedTeam);

        List<Team> teams = teamRepository.findAll();
        assertThat(teams).hasSize(dataBaseSizeBeforeDelete - 1);
    }
}

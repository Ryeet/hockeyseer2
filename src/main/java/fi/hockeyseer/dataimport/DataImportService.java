package fi.hockeyseer.dataimport;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import fi.hockeyseer.dataimport.model.JsonGame;
import fi.hockeyseer.dataimport.model.JsonGameDate;
import fi.hockeyseer.dataimport.model.JsonGameScore;
import fi.hockeyseer.dataimport.model.JsonSeason;
import fi.hockeyseer.domain.Game;
import fi.hockeyseer.domain.Result;
import fi.hockeyseer.domain.Team;
import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.shared.ResultService;

/**
 * Created by alekstu on 5.6.2017.
 */

@Service
public class DataImportService {

	private static Logger log = LoggerFactory.getLogger(DataImportService.class);

	private final GameRepository gameRepository;

	private final ResultService resultService;

	private final TeamRepository teamRepository;

	private final NhlApiClient nhlApiClient;
	
	private	final ExecutorService executorService = Executors.newFixedThreadPool(3);

	@Autowired
	public DataImportService(
			final GameRepository gameRepository, 
			final ResultService resultService,
			final TeamRepository teamRepository, 
			final NhlApiClient nhlApiClient) {
		this.gameRepository = gameRepository;
		this.teamRepository = teamRepository;
		this.resultService = resultService;
		this.nhlApiClient = nhlApiClient;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		log.debug("----------IMPORT DATA START--------------");
		final List<ImportSeason> listImportSeasons =  Arrays.asList(ImportSeason.SEASON_2018_2019);
		importSeasons(listImportSeasons);
		log.debug("----------IMPORT DATA END--------------");
	}
	
	@PreDestroy
	public void destruct() {
		log.debug("Destroying executor");
		this.executorService.shutdownNow();
	}

	public void importSeasons(final List<ImportSeason> seasons) {
		
		for (final ImportSeason season : seasons) {
			executorService.submit(() -> {
				try {
					importSingleSeason(season);
				} catch (IOException e) {
					log.debug("Failed to import season {}", season.name());
					e.printStackTrace();
				}
			});
		}
	}

	/**
	 * Call API and import single season to database
	 * @param importSeason
	 * @throws IOException
	 */
	private void importSingleSeason(final ImportSeason importSeason) throws IOException {
		if (seasonIsInDatabase(importSeason)) {
			return;
		}

		final JsonSeason season = nhlApiClient.getSeason(importSeason.getUrl());

		season.getJsonGameDates()
			.stream()
			.map(JsonGameDate::getJsonGames)
			.flatMap(List::stream)
			.filter(game -> "R".equals(game.getGameType()))
			.map(this::jsonGameToGame)
			.map(gameRepository::save)
			.forEach(this::logGame);	
	}
	
	
	/**
	 * Return true if database contains game(s) for this season.
	 * @param season
	 * @return
	 */
	public boolean seasonIsInDatabase(final ImportSeason season) {
		return gameRepository.existsBySeason(season.getDatabaseFormat());
	}
	

	/**
	 * Convert JsonGame to Game.
	 * @param game
	 * @return
	 */
	private Game jsonGameToGame(final JsonGame game) {
		final Game newGame = new Game()
				.externalId(Long.valueOf(game.getGamePk()))
				.date(ZonedDateTime.parse(game.getGameDate()).toLocalDateTime().plusHours(2))
				.homeTeam(getHomeTeam(game))
				.visitorTeam(getAwayTeam(game))
				.season(game.getSeason())
				.played(false);

		if (game.getJsonGameScore().getJsonGamePeriods().size() != 0) {
			newGame.played(true)
			.result(getGameResult(game.getJsonGameScore()))
			.winner(determineWinner(newGame.getResult().getHome_total(), newGame.getResult().getVisitor_total()));
		}
		return newGame;
	}
	
	private Team getHomeTeam(final JsonGame game) {
		return teamRepository.findByName(game.getJsonGameScore().getTeams().getHome().getTeam().getName());
	}
	
	private Team getAwayTeam(final JsonGame game) {
		return teamRepository.findByName(game.getJsonGameScore().getTeams().getAway().getTeam().getName());
	}

	private Result getGameResult(final JsonGameScore jsonGameScore) {
		final Result result = new Result();
			jsonGameScore.getJsonGamePeriods().stream().forEach(period -> {

			switch (period.getNum()) {
			case 1:
				result.setHome_1st(period.getHome().getGoals());
				result.setVisitor_1st(period.getAway().getGoals());
			case 2:
				result.setHome_2nd(period.getHome().getGoals());
				result.setVisitor_2nd(period.getAway().getGoals());
			case 3:
				result.setHome_3rd(period.getHome().getGoals());
				result.setVisitor_3rd(period.getAway().getGoals());

			}
			result.setHome_total(result.getHome_1st() + result.getHome_2nd() + result.getHome_3rd());
			result.setVisitor_total(result.getVisitor_1st() + result.getVisitor_2nd() + result.getVisitor_3rd());

		});
		result.setHome_shots(jsonGameScore.getTeams().getHome().getShotsOnGoal());
		result.setVisitor_shots(jsonGameScore.getTeams().getAway().getShotsOnGoal());
		final Result saved = resultService.save(result);
		return saved;
	}

	private Integer determineWinner(final Integer homeTotalScore, final Integer visitorTotalScore) {
		return homeTotalScore == visitorTotalScore ? 0 : (homeTotalScore > visitorTotalScore) ? 1 : 2;
	}


	private void logGame(final Game game) {
		if (game.getPlayed()) {
			log.debug("SAVED GAME - Season: ({}): {} vs {} ({}-{}), was played on {}", 
					game.getSeason(), 
					game.getHomeTeam().getName(), 
					game.getVisitorTeam().getName(),
					game.getResult().getHome_total(),
					game.getResult().getVisitor_total(),
					game.getDate());
		} else {
			log.debug("SAVED GAME - Season: ({}): {} vs {}, will be played on {}", 
					game.getSeason(), 
					game.getHomeTeam(), 
					game.getVisitorTeam(),
					game.getDate());
		}

	}

}

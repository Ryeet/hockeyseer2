package fi.hockeyseer.service.shared;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {


    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Game> findUpComingGames()
    {
        LocalDateTime date = LocalDateTime.now().atZone(ZoneId.of("Europe/Helsinki")).minusHours(9).toLocalDateTime();
        return gameRepository.find50NextUpComingGames(date);
    }

    public List<Game> findLatestPlayedGames()
    {
        LocalDateTime date = LocalDateTime.now().atZone(ZoneId.of("Europe/Helsinki")).minusHours(9).toLocalDateTime();
        return gameRepository.find50LatestPlayedGames(true);
    }
}

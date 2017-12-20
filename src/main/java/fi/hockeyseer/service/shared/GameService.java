package fi.hockeyseer.service.shared;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    public List<Game> findUpComingGames() {
        gameRepository.findNextUpComingGame().map(g -> {
          return gameRepository.findByDateAndPlayedIsFalse(g.getDate());

        });
        return new ArrayList<>();
    }

}

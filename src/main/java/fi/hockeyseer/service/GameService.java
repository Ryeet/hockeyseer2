package fi.hockeyseer.service;

import fi.hockeyseer.domain.Game;
import fi.hockeyseer.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {


    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }



    public List<Game> findUpComingGames() {
        return gameRepository.findByDateAndPlayedIsFalse(gameRepository.findNextUpComingGame().getDate());
    }

}

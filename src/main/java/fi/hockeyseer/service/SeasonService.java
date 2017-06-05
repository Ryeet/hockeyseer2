package fi.hockeyseer.service;

import fi.hockeyseer.repository.GameRepository;
import fi.hockeyseer.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alekstu on 5.6.2017.
 */

@Service
public class SeasonService {


    private final GameRepository gameRepository;

    private final ResultRepository resultRepository;


    @Autowired
    public SeasonService(GameRepository gameRepository, ResultRepository resultRepository) {
        this.gameRepository = gameRepository;
        this.resultRepository = resultRepository;
    }

}

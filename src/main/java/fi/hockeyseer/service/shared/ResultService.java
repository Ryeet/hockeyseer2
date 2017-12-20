package fi.hockeyseer.service.shared;

import fi.hockeyseer.domain.Result;
import fi.hockeyseer.repository.ResultRepository;
import fi.hockeyseer.service.dataimport.json.JsonGameScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by alekstu on 5.6.2017.
 */

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }



    public Result save(Result result) {
        Result saved = resultRepository.save(result);
        return saved;
    }
}

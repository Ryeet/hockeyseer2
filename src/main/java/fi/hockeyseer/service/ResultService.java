package fi.hockeyseer.service;

import fi.hockeyseer.domain.Result;
import fi.hockeyseer.repository.ResultRepository;
import fi.hockeyseer.service.json.JsonGameScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Result getGameResult(JsonGameScore jsonGameScore) {
        Result result = new Result();
        jsonGameScore.getJsonGamePeriods()
                .stream()
                .forEach(period -> {

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
            Result saved = resultRepository.save(result);
        return saved;
    }
}

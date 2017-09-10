package fi.hockeyseer.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olli Simanainen on 8.9.2017.
 */
public class ResultsMap {

    private Map<String, Long> results = new HashMap<String, Long>();

    public Map<String, Long> getResults() {
        return results;
    }

    public void setResults(Map<String, Long> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultsMap{" +
                "results=" + results +
                '}';
    }

    public ResultsMap()
    {
        this.results.put("result00", 0L);
        this.results.put("result11", 0L);
        this.results.put("result22", 0L);
        this.results.put("result33", 0L);
        this.results.put("result44", 0L);
        this.results.put("result55", 0L);
        this.results.put("result66", 0L);

        this.results.put("result10team", 0L);
        this.results.put("result20team", 0L);
        this.results.put("result30team", 0L);
        this.results.put("result40team", 0L);
        this.results.put("result50team", 0L);
        this.results.put("result60team", 0L);

        this.results.put("result21team", 0L);
        this.results.put("result31team", 0L);
        this.results.put("result41team", 0L);
        this.results.put("result51team", 0L);
        this.results.put("result61team", 0L);

        this.results.put("result32team", 0L);
        this.results.put("result42team", 0L);
        this.results.put("result52team", 0L);
        this.results.put("result62team", 0L);

        this.results.put("result43team", 0L);
        this.results.put("result53team", 0L);
        this.results.put("result63team", 0L);

        this.results.put("result54team", 0L);
        this.results.put("result64team", 0L);

        this.results.put("result65team", 0L);

        this.results.put("result10against", 0L);
        this.results.put("result20against", 0L);
        this.results.put("result30against", 0L);
        this.results.put("result40against", 0L);
        this.results.put("result50against", 0L);
        this.results.put("result60against", 0L);

        this.results.put("result21against", 0L);
        this.results.put("result31against", 0L);
        this.results.put("result41against", 0L);
        this.results.put("result51against", 0L);
        this.results.put("result61against", 0L);

        this.results.put("result32against", 0L);
        this.results.put("result42against", 0L);
        this.results.put("result52against", 0L);
        this.results.put("result62against", 0L);

        this.results.put("result43against", 0L);
        this.results.put("result53against", 0L);
        this.results.put("result63against", 0L);

        this.results.put("result54against", 0L);
        this.results.put("result64against", 0L);

        this.results.put("result65against", 0L);

        this.results.put("resultElseTeam", 0L);
        this.results.put("resultElseAgainst", 0L);
        this.results.put("resultElseTie", 0L);
    }


}

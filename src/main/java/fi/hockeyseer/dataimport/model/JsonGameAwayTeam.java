
package fi.hockeyseer.dataimport.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "team",
    "goals",
    "shotsOnGoal"
})
public class JsonGameAwayTeam {

    @JsonProperty("team")
    private JsonGameAwayTeamDetails team;
    @JsonProperty("goals")
    private Integer goals;
    @JsonProperty("shotsOnGoal")
    private Integer shotsOnGoal;


    @JsonProperty("team")
    public JsonGameAwayTeamDetails getTeam() {
        return team;
    }

    @JsonProperty("team")
    public void setTeam(JsonGameAwayTeamDetails team) {
        this.team = team;
    }

    @JsonProperty("goals")
    public Integer getGoals() {
        return goals;
    }

    @JsonProperty("goals")
    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    @JsonProperty("shotsOnGoal")
    public Integer getShotsOnGoal() {
        return shotsOnGoal;
    }

    @JsonProperty("shotsOnGoal")
    public void setShotsOnGoal(Integer shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
    }


    @Override
    public String toString() {
        return "JsonGameAwayTeam{" +
                "team=" + team +
                ", goals=" + goals +
                ", shotsOnGoal=" + shotsOnGoal +
                '}';
    }
}

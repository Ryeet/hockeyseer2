
package fi.hockeyseer.dataimport.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "goals",
    "shotsOnGoal"
})
public class JsonPeriodHomeScore {

    @JsonProperty("goals")
    private Integer goals;
    @JsonProperty("shotsOnGoal")
    private Integer shotsOnGoal;



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
        return "JsonPeriodHomeScore{" +
                "goals=" + goals +
                ", shotsOnGoal=" + shotsOnGoal +
                '}';
    }
}

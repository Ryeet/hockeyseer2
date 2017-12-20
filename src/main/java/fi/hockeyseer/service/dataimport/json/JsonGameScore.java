
package fi.hockeyseer.service.dataimport.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "jsonGamePeriods",
    "teams"
})
public class JsonGameScore {

    @JsonProperty("periods")
    private List<JsonGamePeriod> jsonGamePeriods = null;
    @JsonProperty("teams")
    private JsonGameTeams teams;


    @JsonProperty("periods")
    public List<JsonGamePeriod> getJsonGamePeriods() {
        return jsonGamePeriods;
    }

    @JsonProperty("periods")
    public void setJsonGamePeriods(List<JsonGamePeriod> jsonGamePeriods) {
        this.jsonGamePeriods = jsonGamePeriods;
    }

    @JsonProperty("teams")
    public JsonGameTeams getTeams() {
        return teams;
    }

    @JsonProperty("teams")
    public void setTeams(JsonGameTeams teams) {
        this.teams = teams;
    }


    @Override
    public String toString() {
        return "JsonGameScore{" +
                "jsonGamePeriods=" + jsonGamePeriods +
                ", teams=" + teams +
                '}';
    }
}

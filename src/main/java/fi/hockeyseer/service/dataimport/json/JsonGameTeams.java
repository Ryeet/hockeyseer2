
package fi.hockeyseer.service.dataimport.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "home",
    "away"
})
public class JsonGameTeams {

    @JsonProperty("home")
    private JsonGameHomeTeam home;
    @JsonProperty("away")
    private JsonGameAwayTeam away;

    @JsonProperty("home")
    public JsonGameHomeTeam getHome() {
        return home;
    }

    @JsonProperty("home")
    public void setHome(JsonGameHomeTeam home) {
        this.home = home;
    }

    @JsonProperty("away")
    public JsonGameAwayTeam getAway() {
        return away;
    }

    @JsonProperty("away")
    public void setAway(JsonGameAwayTeam away) {
        this.away = away;
    }

    @Override
    public String toString() {
        return "JsonGameTeams{" +
                "home=" + home +
                ", away=" + away +
                '}';
    }
}

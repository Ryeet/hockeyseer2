
package fi.hockeyseer.service.dataimport.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "jsonGames"
})
@SuppressWarnings("unused")
public class JsonGameDate {

    @JsonProperty("date")
    private String date;

    @JsonProperty("games")
    private List<JsonGame> jsonGames = null;

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("games")
    public List<JsonGame> getJsonGames() {
        return jsonGames;
    }

    @JsonProperty("games")
    public void setJsonGames(List<JsonGame> jsonGames) {
        this.jsonGames = jsonGames;
    }

    @Override
    public String toString() {
        return "JsonGameDate{" +
                "date='" + date + '\'' +
                ", jsonGames=" + jsonGames +
                '}';
    }
}

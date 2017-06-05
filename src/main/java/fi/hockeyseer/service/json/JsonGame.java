
package fi.hockeyseer.service.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gamePk",
    "gameType",
    "season",
    "gameDate",
    "teams",
    "jsonGameScore"
})
@SuppressWarnings("unused")
public class JsonGame {

    @JsonProperty("gamePk")
    private Integer gamePk;
    @JsonProperty("gameType")
    private String gameType;
    @JsonProperty("season")
    private String season;
    @JsonProperty("gameDate")
    private String gameDate;
    @JsonProperty("linescore")
    private JsonGameScore jsonGameScore;

    @JsonProperty("gamePk")
    public Integer getGamePk() {
        return gamePk;
    }

    @JsonProperty("gamePk")
    public void setGamePk(Integer gamePk) {
        this.gamePk = gamePk;
    }

    @JsonProperty("gameType")
    public String getGameType() {
        return gameType;
    }

    @JsonProperty("gameType")
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @JsonProperty("season")
    public String getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(String season) {
        this.season = season;
    }

    @JsonProperty("gameDate")
    public String getGameDate() {
        return gameDate;
    }

    @JsonProperty("gameDate")
    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    @JsonProperty("linescore")
    public JsonGameScore getJsonGameScore() {
        return jsonGameScore;
    }

    @JsonProperty("linescore")
    public void setJsonGameScore(JsonGameScore jsonGameScore) {
        this.jsonGameScore = jsonGameScore;
    }


    @Override
    public String toString() {
        return "JsonGame{" +
                "gamePk=" + gamePk +
                ", gameType='" + gameType + '\'' +
                ", season='" + season + '\'' +
                ", gameDate='" + gameDate + '\'' +
                ", jsonGameScore=" + jsonGameScore +
                '}';
    }
}

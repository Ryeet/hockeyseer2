
package fi.hockeyseer.service.dataimport.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "periodType",
    "num",
    "home",
    "away"
})
public class JsonGamePeriod {

    @JsonProperty("periodType")
    private String periodType;
    @JsonProperty("num")
    private Integer num;
    @JsonProperty("home")
    private JsonPeriodHomeScore home;
    @JsonProperty("away")
    private JsonPeriodAwayScore away;

    @JsonProperty("periodType")
    public String getPeriodType() {
        return periodType;
    }

    @JsonProperty("periodType")
    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("home")
    public JsonPeriodHomeScore getHome() {
        return home;
    }

    @JsonProperty("home")
    public void setHome(JsonPeriodHomeScore home) {
        this.home = home;
    }

    @JsonProperty("away")
    public JsonPeriodAwayScore getAway() {
        return away;
    }

    @JsonProperty("away")
    public void setAway(JsonPeriodAwayScore away) {
        this.away = away;
    }


    @Override
    public String toString() {
        return "JsonGamePeriod{" +
                "periodType='" + periodType + '\'' +
                ", num=" + num +
                ", home=" + home +
                ", away=" + away +
                '}';
    }
}


package fi.hockeyseer.dataimport.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "jsonGameDates"
})
@SuppressWarnings("unused")
public class JsonSeason {


    @JsonProperty("dates")
    private List<JsonGameDate> jsonGameDates = null;

    @JsonProperty("jsonGameDates")
    public List<JsonGameDate> getJsonGameDates() {
        return jsonGameDates;
    }

    @JsonProperty("jsonGameDates")
    public void setJsonGameDates(List<JsonGameDate> jsonGameDates) {
        this.jsonGameDates = jsonGameDates;
    }


    @Override
    public String toString() {
        return "JsonSeason{" +
                "jsonGameDates=" + jsonGameDates +
                '}';
    }
}

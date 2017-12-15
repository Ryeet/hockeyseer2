package fi.hockeyseer.web.forms;

import java.util.List;

/**
 * Created by LickiLicki on 26-Aug-17.
 */
public class SearchToolForm {

    private Long team;
    private long againstSelect;
    private List<String> againstTeam;
    private List<String> againstDivision;
    private List<String> againstConference;
    private List<String> season;

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }

    public long getAgainstSelect() {
        return againstSelect;
    }

    public void setAgainstSelect(long againstSelect) {
        this.againstSelect = againstSelect;
    }

    public List<String> getAgainstTeam() {
        return againstTeam;
    }

    public void setAgainstTeam(List<String> againstTeam) {
        this.againstTeam = againstTeam;
    }

    public List<String> getAgainstDivision() {
        return againstDivision;
    }

    public void setAgainstDivision(List<String> againstDivision) {
        this.againstDivision = againstDivision;
    }

    public List<String> getAgainstConference() {
        return againstConference;
    }

    public void setAgainstConference(List<String> againstConference) {
        this.againstConference = againstConference;
    }

    public List<String> getSeason() {
        return season;
    }

    public void setSeason(List<String> season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "SearchToolForm{" +
                "team=" + team +
                ", againstSelect=" + againstSelect +
                ", againstTeam=" + againstTeam +
                ", againstDivision=" + againstDivision +
                ", againstConference=" + againstConference +
                ", season=" + season +
                '}';
    }
}

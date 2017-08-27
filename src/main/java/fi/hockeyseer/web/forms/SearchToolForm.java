package fi.hockeyseer.web.forms;

import java.util.List;

/**
 * Created by LickiLicki on 26-Aug-17.
 */
public class SearchToolForm {

    private String team;
    private Long againstSelect;
    private List<String> againstTeam;
    private List<String> againstDivision;
    private List<String> againstConference;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getAgainstSelect() {
        return againstSelect;
    }

    public void setAgainstSelect(Long againstSelect) {
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

    @Override
    public String toString() {
        return "SearchToolForm{" +
                "team='" + team + '\'' +
                ", againstSelect=" + againstSelect +
                ", againstTeam=" + againstTeam +
                ", againstDivision=" + againstDivision +
                ", againstConference=" + againstConference +
                '}';
    }
}

package fi.hockeyseer.service.data.stats.team;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.service.data.stats.basic.MarginStats;
import fi.hockeyseer.service.data.stats.basic.PercentageStats;

import java.util.Map;

public class TeamStats {

    private Team team;
    private PercentageStats percentageStats;
    private Map<String, MarginStats> marginStats;


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    public PercentageStats getPercentageStats() {
        return percentageStats;
    }

    public void setPercentageStats(PercentageStats percentageStats) {
        this.percentageStats = percentageStats;
    }

    public Map<String, MarginStats> getMarginStats() {
        return marginStats;
    }

    public void setMarginStats(Map<String, MarginStats> marginStats) {
        this.marginStats = marginStats;
    }

    @Override
    public String toString() {
        return "TeamStats{" +
                "team=" + team +
                ", percentageStats=" + percentageStats +
                ", marginStats=" + marginStats +
                '}';
    }
}

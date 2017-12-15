package fi.hockeyseer.service.data.stats.team;

import fi.hockeyseer.domain.Team;
import fi.hockeyseer.service.data.stats.basic.MarginStats;
import fi.hockeyseer.service.data.stats.basic.PercentageStats;

import java.util.List;
import java.util.Map;

public class TeamStats {

    private Team team;
    private Map<String, MarginStats> marginStats;
    private PercentageStats percentageStatsAll;
    private PercentageStats percentageStatsHome;
    private PercentageStats percentageStatsVisitor;
    private TendencyStats tendencyStatsAll;
    private TendencyStats tendencyStatsHome;
    private TendencyStats tendencyStatsVisitor;
    private AdjustedPowers adjustedPowersAll;
    private AdjustedPowers adjustedPowersHome;
    private AdjustedPowers adjustedPowersVisitor;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Map<String, MarginStats> getMarginStats() {
        return marginStats;
    }

    public void setMarginStats(Map<String, MarginStats> marginStats) {
        this.marginStats = marginStats;
    }

    public PercentageStats getPercentageStatsAll() {
        return percentageStatsAll;
    }

    public void setPercentageStatsAll(PercentageStats percentageStatsAll) {
        this.percentageStatsAll = percentageStatsAll;
    }

    public PercentageStats getPercentageStatsHome() {
        return percentageStatsHome;
    }

    public void setPercentageStatsHome(PercentageStats percentageStatsHome) {
        this.percentageStatsHome = percentageStatsHome;
    }

    public PercentageStats getPercentageStatsVisitor() {
        return percentageStatsVisitor;
    }

    public void setPercentageStatsVisitor(PercentageStats percentageStatsVisitor) {
        this.percentageStatsVisitor = percentageStatsVisitor;
    }

    public TendencyStats getTendencyStatsAll() {
        return tendencyStatsAll;
    }

    public void setTendencyStatsAll(TendencyStats tendencyStatsAll) {
        this.tendencyStatsAll = tendencyStatsAll;
    }

    public TendencyStats getTendencyStatsHome() {
        return tendencyStatsHome;
    }

    public void setTendencyStatsHome(TendencyStats tendencyStatsHome) {
        this.tendencyStatsHome = tendencyStatsHome;
    }

    public TendencyStats getTendencyStatsVisitor() {
        return tendencyStatsVisitor;
    }

    public void setTendencyStatsVisitor(TendencyStats tendencyStatsVisitor) {
        this.tendencyStatsVisitor = tendencyStatsVisitor;
    }

    public AdjustedPowers getAdjustedPowersAll() {
        return adjustedPowersAll;
    }

    public void setAdjustedPowersAll(AdjustedPowers adjustedPowersAll) {
        this.adjustedPowersAll = adjustedPowersAll;
    }

    public AdjustedPowers getAdjustedPowersHome() {
        return adjustedPowersHome;
    }

    public void setAdjustedPowersHome(AdjustedPowers adjustedPowersHome) {
        this.adjustedPowersHome = adjustedPowersHome;
    }

    public AdjustedPowers getAdjustedPowersVisitor() {
        return adjustedPowersVisitor;
    }

    public void setAdjustedPowersVisitor(AdjustedPowers adjustedPowersVisitor) {
        this.adjustedPowersVisitor = adjustedPowersVisitor;
    }

    @Override
    public String toString() {
        return "TeamStats{" +
                "team=" + team +
                ", marginStats=" + marginStats +
                ", percentageStatsAll=" + percentageStatsAll +
                ", percentageStatsHome=" + percentageStatsHome +
                ", percentageStatsVisitor=" + percentageStatsVisitor +
                ", tendencyStatsAll=" + tendencyStatsAll +
                ", tendencyStatsHome=" + tendencyStatsHome +
                ", tendencyStatsVisitor=" + tendencyStatsVisitor +
                ", adjustedPowersAll=" + adjustedPowersAll +
                ", adjustedPowersHome=" + adjustedPowersHome +
                ", adjustedPowersVisitor=" + adjustedPowersVisitor +
                '}';
    }
}

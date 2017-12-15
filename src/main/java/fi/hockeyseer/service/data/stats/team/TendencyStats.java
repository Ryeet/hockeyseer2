package fi.hockeyseer.service.data.stats.team;

import fi.hockeyseer.service.data.stats.basic.PercentageStats;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by LickiLicki on 02-Oct-17.
 */
public class TendencyStats {

    private BigDecimal winTendency;
    private BigDecimal lossTendency;
    private BigDecimal tieTendency;
    private BigDecimal scorePowerTendency;
    private BigDecimal defenseSuckTendency;

    public BigDecimal getWinTendency() {
        return winTendency;
    }

    public void setWinTendency(BigDecimal winTendency) {
        this.winTendency = winTendency;
    }

    public BigDecimal getLossTendency() {
        return lossTendency;
    }

    public void setLossTendency(BigDecimal lossTendency) {
        this.lossTendency = lossTendency;
    }

    public BigDecimal getTieTendency() {
        return tieTendency;
    }

    public void setTieTendency(BigDecimal tieTendency) {
        this.tieTendency = tieTendency;
    }

    public BigDecimal getScorePowerTendency() {
        return scorePowerTendency;
    }

    public void setScorePowerTendency(BigDecimal scorePowerTendency) {
        this.scorePowerTendency = scorePowerTendency;
    }

    public BigDecimal getDefenseSuckTendency() {
        return defenseSuckTendency;
    }

    public void setDefenseSuckTendency(BigDecimal defenseSuckTendency) {
        this.defenseSuckTendency = defenseSuckTendency;
    }

    public TendencyStats setTendencies(PercentageStats percentageStats, LeagueAvgStats leagueAvgStats)
    {
        setWinTendency(percentageStats.getWinPercentage().divide(leagueAvgStats.getAvgWinPercentage(), MathContext.DECIMAL32));
        setLossTendency(percentageStats.getLossPercentage().divide(leagueAvgStats.getAvgLossPercentage(), MathContext.DECIMAL32));
        setTieTendency(percentageStats.getTiePercentage().divide(leagueAvgStats.getAvgTiePercentage(), MathContext.DECIMAL32));
        setScorePowerTendency(percentageStats.getGoalsForAverage().divide(leagueAvgStats.getAvgGoalsFor(), MathContext.DECIMAL32));
        setDefenseSuckTendency(percentageStats.getGoalsAgainstAverage().divide(leagueAvgStats.getAvgGoalsAgainst(), MathContext.DECIMAL32));
        return this;
    }

    @Override
    public String toString() {
        return "TendencyStats{" +
                "winTendency=" + winTendency +
                ", lossTendency=" + lossTendency +
                ", tieTendency=" + tieTendency +
                ", scorePowerTendency=" + scorePowerTendency +
                ", defenseSuckTendency=" + defenseSuckTendency +
                '}';
    }
}

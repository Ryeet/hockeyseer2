package fi.hockeyseer.service.data.stats.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by LickiLicki on 17-Sep-17.
 */
public class PercentageStats extends BasicStats{

    private static Logger log = LoggerFactory.getLogger(PercentageStats.class);

    private BigDecimal winPercentage;
    private BigDecimal lossPercentage;
    private BigDecimal tiePercentage;
    private BigDecimal goalsForAverage;
    private BigDecimal goalsAgainstAverage;

    public PercentageStats(MarginStats marginStats) {
        this.winPercentage = BigDecimal.valueOf(marginStats.getWins()).divide(BigDecimal.valueOf(marginStats.getGameCount()), MathContext.DECIMAL32);
        this.lossPercentage = BigDecimal.valueOf(marginStats.getLosses()).divide(BigDecimal.valueOf(marginStats.getGameCount()), MathContext.DECIMAL32);
        this.tiePercentage = BigDecimal.valueOf(marginStats.getTies()).divide(BigDecimal.valueOf(marginStats.getGameCount()), MathContext.DECIMAL32);
        this.goalsForAverage = BigDecimal.valueOf(marginStats.getGoalsFor()).divide(BigDecimal.valueOf(marginStats.getGameCount()), MathContext.DECIMAL32);
        this.goalsAgainstAverage = BigDecimal.valueOf(marginStats.getGoalsAgainst()).divide(BigDecimal.valueOf(marginStats.getGameCount()), MathContext.DECIMAL32);
    }

    public BigDecimal getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(BigDecimal winPercentage) {
        this.winPercentage = winPercentage;
    }

    public BigDecimal getLossPercentage() {
        return lossPercentage;
    }

    public void setLossPercentage(BigDecimal lossPercentage) {
        this.lossPercentage = lossPercentage;
    }

    public BigDecimal getTiePercentage() {
        return tiePercentage;
    }

    public void setTiePercentage(BigDecimal tiePercentage) {
        this.tiePercentage = tiePercentage;
    }

    public BigDecimal getGoalsForAverage() {
        return goalsForAverage;
    }

    public void setGoalsForAverage(BigDecimal goalsForAverage) {
        this.goalsForAverage = goalsForAverage;
    }

    public BigDecimal getGoalsAgainstAverage() {
        return goalsAgainstAverage;
    }

    public void setGoalsAgainstAverage(BigDecimal goalsAgainstAverage) {
        this.goalsAgainstAverage = goalsAgainstAverage;
    }

    @Override
    public String toString() {
        return "PercentageStats{" +
                "winPercentage=" + winPercentage +
                ", lossPercentage=" + lossPercentage +
                ", tiePercentage=" + tiePercentage +
                ", goalsForAverage=" + goalsForAverage +
                ", goalsAgainstAverage=" + goalsAgainstAverage +
                '}';
    }
}

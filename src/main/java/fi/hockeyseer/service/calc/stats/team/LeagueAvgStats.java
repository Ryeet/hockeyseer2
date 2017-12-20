package fi.hockeyseer.service.calc.stats.team;

import fi.hockeyseer.service.calc.stats.basic.PercentageStats;

import java.math.BigDecimal;
import java.math.MathContext;

public class LeagueAvgStats{

    private BigDecimal avgWinPercentage;
    private BigDecimal avgLossPercentage;
    private BigDecimal avgTiePercentage;
    private BigDecimal avgGoalsFor;
    private BigDecimal avgGoalsAgainst;

    private BigDecimal winPercentageSum = BigDecimal.ZERO;
    private BigDecimal lossPercentageSum = BigDecimal.ZERO;
    private BigDecimal tiePercentageSum = BigDecimal.ZERO;
    private BigDecimal goalsForSum = BigDecimal.ZERO;
    private BigDecimal goalsAgainstSum = BigDecimal.ZERO;

    public BigDecimal getAvgWinPercentage() {
        return avgWinPercentage;
    }

    public void setAvgWinPercentage(BigDecimal avgWinPercentage) {
        this.avgWinPercentage = avgWinPercentage;
    }

    public BigDecimal getAvgLossPercentage() {
        return avgLossPercentage;
    }

    public void setAvgLossPercentage(BigDecimal avgLossPercentage) {
        this.avgLossPercentage = avgLossPercentage;
    }

    public BigDecimal getAvgTiePercentage() {
        return avgTiePercentage;
    }

    public void setAvgTiePercentage(BigDecimal avgTiePercentage) {
        this.avgTiePercentage = avgTiePercentage;
    }

    public BigDecimal getAvgGoalsFor() {
        return avgGoalsFor;
    }

    public void setAvgGoalsFor(BigDecimal avgGoalsFor) {
        this.avgGoalsFor = avgGoalsFor;
    }

    public BigDecimal getAvgGoalsAgainst() {
        return avgGoalsAgainst;
    }

    public void setAvgGoalsAgainst(BigDecimal avgGoalsAgainst) {
        this.avgGoalsAgainst = avgGoalsAgainst;
    }

    public BigDecimal getWinPercentageSum() {
        return winPercentageSum;
    }

    public void setWinPercentageSum(BigDecimal winPercentageSum) {
        BigDecimal sum = this.winPercentageSum.add(winPercentageSum);
        this.winPercentageSum = sum;
    }

    public BigDecimal getLossPercentageSum() {
        return lossPercentageSum;
    }

    public void setLossPercentageSum(BigDecimal lossPercentageSum) {
        BigDecimal sum = this.lossPercentageSum.add(lossPercentageSum);
        this.lossPercentageSum = sum;
    }

    public BigDecimal getTiePercentageSum() {
        return tiePercentageSum;
    }

    public void setTiePercentageSum(BigDecimal tiePercentageSum) {
        BigDecimal sum = this.tiePercentageSum.add(tiePercentageSum);
        this.tiePercentageSum = sum;
    }

    public BigDecimal getGoalsForSum() {
        return goalsForSum;
    }

    public void setGoalsForSum(BigDecimal goalsForSum) {
        BigDecimal sum = this.goalsForSum.add(goalsForSum);
        this.goalsForSum = sum;
    }

    public BigDecimal getGoalsAgainstSum() {
        return goalsAgainstSum;
    }

    public void setGoalsAgainstSum(BigDecimal goalsAgainstSum) {
        BigDecimal sum = this.goalsAgainstSum.add(goalsAgainstSum);
        this.goalsAgainstSum = sum;
    }

    public void increaseAvgSums(PercentageStats percentageStats)
    {
        setWinPercentageSum(percentageStats.getWinPercentage());
        setLossPercentageSum(percentageStats.getLossPercentage());
        setTiePercentageSum(percentageStats.getTiePercentage());
        setGoalsForSum(percentageStats.getGoalsForAverage());
        setGoalsAgainstSum(percentageStats.getGoalsAgainstAverage());
    }

    public LeagueAvgStats setAverages(Integer teamCount)
    {
        setAvgWinPercentage(getWinPercentageSum().divide(new BigDecimal(teamCount), MathContext.DECIMAL32));
        setAvgLossPercentage(getLossPercentageSum().divide(new BigDecimal(teamCount), MathContext.DECIMAL32));
        setAvgTiePercentage(getTiePercentageSum().divide(new BigDecimal(teamCount), MathContext.DECIMAL32));
        setAvgGoalsFor(getGoalsForSum().divide(new BigDecimal(teamCount), MathContext.DECIMAL32));
        setAvgGoalsAgainst(getGoalsAgainstSum().divide(new BigDecimal(teamCount), MathContext.DECIMAL32));
        return this;
    }

    @Override
    public String toString() {
        return "LeagueAvgStats{" +
                "avgWinPercentage=" + avgWinPercentage +
                ", avgLossPercentage=" + avgLossPercentage +
                ", avgTiePercentage=" + avgTiePercentage +
                ", avgGoalsFor=" + avgGoalsFor +
                ", avgGoalsAgainst=" + avgGoalsAgainst +
                ", winPercentageSum=" + winPercentageSum +
                ", lossPercentageSum=" + lossPercentageSum +
                ", tiePercentageSum=" + tiePercentageSum +
                ", goalsForSum=" + goalsForSum +
                ", goalsAgainstSum=" + goalsAgainstSum +
                '}';
    }
}

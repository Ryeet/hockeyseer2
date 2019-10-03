package fi.hockeyseer.service.calc.stats.basic;

import java.math.BigDecimal;

public class PredictionStats
{
    private BigDecimal homeWin;
    private BigDecimal tie;
    private BigDecimal awayWin;

    public BigDecimal getHomeWin() {
        return homeWin;
    }
    public void setHomeWin(BigDecimal homeWin) {
        this.homeWin = homeWin;
    }

    public BigDecimal getTie() {
        return tie;
    }
    public void setTie(BigDecimal tie) {
        this.tie = tie;
    }

    public BigDecimal getAwayWin() {
        return awayWin;
    }
    public void setAwayWin(BigDecimal awayWin) {
        this.awayWin = awayWin;
    }
}

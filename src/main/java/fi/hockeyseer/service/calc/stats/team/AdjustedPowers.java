package fi.hockeyseer.service.calc.stats.team;

import java.math.BigDecimal;

/**
 * Created by LickiLicki on 03-Oct-17.
 */
public class AdjustedPowers
{
    private BigDecimal adjustedWinTendency = BigDecimal.ZERO;
    private BigDecimal adjustedLossTendency = BigDecimal.ZERO;
    private BigDecimal adjustedTieTendency = BigDecimal.ZERO;
    private BigDecimal adjustedScorePower = BigDecimal.ZERO;
    private BigDecimal adjustedDefenseSuck = BigDecimal.ZERO;

    public BigDecimal getAdjustedWinTendency() {
        return adjustedWinTendency;
    }

    public void setAdjustedWinTendency(BigDecimal adjustedWinTendency) {
        this.adjustedWinTendency = adjustedWinTendency;
    }

    public BigDecimal getAdjustedLossTendency() {
        return adjustedLossTendency;
    }

    public void setAdjustedLossTendency(BigDecimal adjustedLossTendency) {
        this.adjustedLossTendency = adjustedLossTendency;
    }

    public BigDecimal getAdjustedTieTendency() {
        return adjustedTieTendency;
    }

    public void setAdjustedTieTendency(BigDecimal adjustedTieTendency) {
        this.adjustedTieTendency = adjustedTieTendency;
    }

    public BigDecimal getAdjustedScorePower() {
        return adjustedScorePower;
    }

    public void setAdjustedScorePower(BigDecimal adjustedScorePower) {
        this.adjustedScorePower = adjustedScorePower;
    }

    public BigDecimal getAdjustedDefenseSuck() {
        return adjustedDefenseSuck;
    }

    public void setAdjustedDefenseSuck(BigDecimal adjustedDefenseSuck) {
        this.adjustedDefenseSuck = adjustedDefenseSuck;
    }

    @Override
    public String toString() {
        return "AdjustedPowers{" +
                "adjustedWinTendency=" + adjustedWinTendency +
                ", adjustedLossTendency=" + adjustedLossTendency +
                ", adjustedTieTendency=" + adjustedTieTendency +
                ", adjustedScorePower=" + adjustedScorePower +
                ", adjustedDefenseSuck=" + adjustedDefenseSuck +
                '}';
    }
}

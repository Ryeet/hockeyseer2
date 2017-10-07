package fi.hockeyseer.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by LickiLicki on 04-Oct-17.
 */
@Entity
@Table(name = "adjustedpower")
public class AdjustedPowerForOlli implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teamId")
    private Long teamId;

    @Column(name = "gameId")
    private Long gameId;

    @Column(name = "adjustedWinTendency", precision=19, scale=7)
    private BigDecimal adjustedWinTendency = BigDecimal.ZERO;

    @Column(name = "adjustedLossTendency", precision=19, scale=7)
    private BigDecimal adjustedLossTendency = BigDecimal.ZERO;

    @Column(name = "adjustedTieTendency", precision=19, scale=7)
    private BigDecimal adjustedTieTendency = BigDecimal.ZERO;

    @Column(name = "adjustedScorePower", precision=19, scale=7)
    private BigDecimal adjustedScorePower = BigDecimal.ZERO;

    @Column(name = "adjustedDefenseSuck", precision=19, scale=7)
    private BigDecimal adjustedDefenseSuck = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdjustedPowerForOlli adjustedPowerForOlli = (AdjustedPowerForOlli) o;

        return id.equals(adjustedPowerForOlli.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "AdjustedPowerForOlli{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", gameId=" + gameId +
                ", adjustedWinTendency=" + adjustedWinTendency +
                ", adjustedLossTendency=" + adjustedLossTendency +
                ", adjustedTieTendency=" + adjustedTieTendency +
                ", adjustedScorePower=" + adjustedScorePower +
                ", adjustedDefenseSuck=" + adjustedDefenseSuck +
                '}';
    }
}

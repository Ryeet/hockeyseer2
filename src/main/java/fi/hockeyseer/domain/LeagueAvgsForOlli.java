package fi.hockeyseer.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by LickiLicki on 05-Oct-17.
 */
@Entity
@Table(name = "leagueavgs")
public class LeagueAvgsForOlli implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "homeWinPercentage", precision=19, scale=7)
    private BigDecimal homeWinPercentage = BigDecimal.ZERO;

    @Column(name = "homeTiePercentage", precision=19, scale=7)
    private BigDecimal homeTiePercentage = BigDecimal.ZERO;

    @Column(name = "homeLossPercentage", precision=19, scale=7)
    private BigDecimal homeLossPercentage = BigDecimal.ZERO;

    @Column(name = "homegfa", precision=19, scale=7)
    private BigDecimal homegfa = BigDecimal.ZERO;

    @Column(name = "homegaa", precision=19, scale=7)
    private BigDecimal homegaa = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getHomeWinPercentage() {
        return homeWinPercentage;
    }

    public void setHomeWinPercentage(BigDecimal homeWinPercentage) {
        this.homeWinPercentage = homeWinPercentage;
    }

    public BigDecimal getHomeTiePercentage() {
        return homeTiePercentage;
    }

    public void setHomeTiePercentage(BigDecimal homeTiePercentage) {
        this.homeTiePercentage = homeTiePercentage;
    }

    public BigDecimal getHomeLossPercentage() {
        return homeLossPercentage;
    }

    public void setHomeLossPercentage(BigDecimal homeLossPercentage) {
        this.homeLossPercentage = homeLossPercentage;
    }

    public BigDecimal getHomegfa() {
        return homegfa;
    }

    public void setHomegfa(BigDecimal homegfa) {
        this.homegfa = homegfa;
    }

    public BigDecimal getHomegaa() {
        return homegaa;
    }

    public void setHomegaa(BigDecimal homegaa) {
        this.homegaa = homegaa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeagueAvgsForOlli leagueAvgsForOlli = (LeagueAvgsForOlli) o;

        return id.equals(leagueAvgsForOlli.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "LeagueAvgsForOlliRepository{" +
                "id=" + id +
                ", date=" + date +
                ", homeWinPercentage=" + homeWinPercentage +
                ", homeTiePercentage=" + homeTiePercentage +
                ", homeLossPercentage=" + homeLossPercentage +
                ", homegfa=" + homegfa +
                ", homegaa=" + homegaa +
                '}';
    }
}

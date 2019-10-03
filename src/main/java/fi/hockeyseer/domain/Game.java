package fi.hockeyseer.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Created by LickiLicki on 03-Jun-17.
 */

@Entity
@Table(name = "game")

public class Game implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "externalId", nullable = false)
    private Long externalId;

    @ManyToOne
    @NotNull
    private Team homeTeam;

    @ManyToOne
    @NotNull
    private Team visitorTeam;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;


    @Column(name = "played", nullable = false, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean played;

    @Column(name = "winner")
    private Integer winner; // 0=draw, 1=home, 2=visitor


    @Column(name = "season", nullable = false)
    @NotNull
    private String season;

    @OneToOne
    @JoinColumn(unique = true)
    private Result result;

    @OneToOne
    private Stats stats;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() { return externalId; }

    public void setExternalId(Long externalId) { this.externalId = externalId; }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Game homeTeam(Team homeTeam) { this.homeTeam = homeTeam; return this;}

    public Game visitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
        return this;
    }

    public Game date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Game played(Boolean played) {
        this.played = played;
        return this;
    }

    public Game winner(Integer winner) {
        this.winner = winner;
        return this;
    }

    public Game season(String season) {
        this.season = season;
        return this;
    }

    public Game result(Result result) {
        this.result = result;
        return this;
    }

    public Game stats(Stats stats) {
        this.stats = stats;
        return this;
    }

    public Game externalId(Long externalId)
    {
        this.externalId = externalId;
        return this;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", homeTeam=" + homeTeam +
                ", visitorTeam=" + visitorTeam +
                ", date=" + date +
                ", played=" + played +
                ", winner=" + winner +
                ", season=" + season +
                ", result=" + result +
                ", stats=" + stats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
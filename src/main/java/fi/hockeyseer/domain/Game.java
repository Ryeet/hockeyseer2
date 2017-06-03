package fi.hockeyseer.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team visitorTeam;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "played", nullable = false)
    private Boolean played;

    @Column(name = "winner")
    private Integer winner; // 0=draw, 1=home, 2=visitor

    @OneToOne
    private Result result;

    @OneToOne
    private Stats stats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //public Team conference(Conference conference) {this.conference = conference; return this;} todo: do this for every attribute under this comment

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", visitorTeam=" + visitorTeam +
                ", date=" + date +
                ", played=" + played +
                ", winner=" + winner +
                '}';
    }
}
package fi.hockeyseer.domain;

import javax.persistence.*;
import java.io.Serializable;
import fi.hockeyseer.domain.enumeration.*;
import fi.hockeyseer.service.data.stats.team.TeamStats;

/**
 * Created by alekstu on 31.5.2017.
 */

@Entity
@Table(name = "team")

public class Team implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "conference", nullable = false)
    private Conference conference;

    @Enumerated(EnumType.STRING)
    @Column(name = "division", nullable = false)
    private Division division;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team name(String name) {this.name = name; return this;}

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Team conference(Conference conference) {this.conference = conference; return this;}

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Team division(Division division) {this.division = division; return this;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return id != null ? id.equals(team.id) : team.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conference=" + conference +
                ", division=" + division +
                '}';
    }
}

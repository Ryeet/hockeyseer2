package fi.hockeyseer.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by LickiLicki on 03-Jun-17.
 */

@Entity
@Table(name = "stats")

public class Stats implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "prediction_date", nullable = false)
    private LocalDate date;

    @Column(name = "prediciton", nullable = false)
    private Integer prediction; // 0=draw,1=home,2=visitor

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPrediction() {
        return prediction;
    }

    public void setPrediction(Integer prediction) {
        this.prediction = prediction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stats stats = (Stats) o;

        return id == stats.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", date=" + date +
                ", prediction=" + prediction +
                '}';
    }
}
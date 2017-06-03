package fi.hockeyseer.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by LickiLicki on 03-Jun-17.
 */

@Entity
@Table(name = "result")

public class Result implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "home_1st", nullable = false)
    private Integer home_1st;

    @Column(name = "home_2nd", nullable = false)
    private Integer home_2nd;

    @Column(name = "home_3rd", nullable = false)
    private Integer home_3rd;

    @Column(name = "home_total", nullable = false)
    private Integer home_total;

    @Column(name = "home_shots", nullable = false)
    private Integer home_shots;

    @Column(name = "visitor_1st", nullable = false)
    private Integer visitor_1st;

    @Column(name = "visitor_2nd", nullable = false)
    private Integer visitor_2nd;

    @Column(name = "visitor_3rd", nullable = false)
    private Integer visitor_3rd;

    @Column(name = "visitor_total", nullable = false)
    private Integer visitor_total;

    @Column(name = "visitor_shots", nullable = false)
    private Integer visitor_shots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHome_1st() {
        return home_1st;
    }

    public void setHome_1st(Integer home_1st) {
        this.home_1st = home_1st;
    }

    public Integer getHome_2nd() {
        return home_2nd;
    }

    public void setHome_2nd(Integer home_2nd) {
        this.home_2nd = home_2nd;
    }

    public Integer getHome_3rd() {
        return home_3rd;
    }

    public void setHome_3rd(Integer home_3rd) {
        this.home_3rd = home_3rd;
    }

    public Integer getHome_total() {
        return home_total;
    }

    public void setHome_total(Integer home_total) {
        this.home_total = home_total;
    }

    public Integer getHome_shots() {
        return home_shots;
    }

    public void setHome_shots(Integer home_shots) {
        this.home_shots = home_shots;
    }

    public Integer getVisitor_1st() {
        return visitor_1st;
    }

    public void setVisitor_1st(Integer visitor_1st) {
        this.visitor_1st = visitor_1st;
    }

    public Integer getVisitor_2nd() {
        return visitor_2nd;
    }

    public void setVisitor_2nd(Integer visitor_2nd) {
        this.visitor_2nd = visitor_2nd;
    }

    public Integer getVisitor_3rd() {
        return visitor_3rd;
    }

    public void setVisitor_3rd(Integer visitor_3rd) {
        this.visitor_3rd = visitor_3rd;
    }

    public Integer getVisitor_total() {
        return visitor_total;
    }

    public void setVisitor_total(Integer visitor_total) {
        this.visitor_total = visitor_total;
    }

    public Integer getVisitor_shots() {
        return visitor_shots;
    }

    public void setVisitor_shots(Integer visitor_shots) {
        this.visitor_shots = visitor_shots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return id.equals(result.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", home_1st=" + home_1st +
                ", home_2nd=" + home_2nd +
                ", home_3rd=" + home_3rd +
                ", home_total=" + home_total +
                ", home_shots=" + home_shots +
                ", visitor_1st=" + visitor_1st +
                ", visitor_2nd=" + visitor_2nd +
                ", visitor_3rd=" + visitor_3rd +
                ", visitor_total=" + visitor_total +
                ", visitor_shots=" + visitor_shots +
                '}';
    }
}
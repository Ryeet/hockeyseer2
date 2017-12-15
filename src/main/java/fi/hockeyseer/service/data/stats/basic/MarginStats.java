package fi.hockeyseer.service.data.stats.basic;

import fi.hockeyseer.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by LickiLicki on 17-Sep-17.
 */
public class MarginStats extends BasicStats {

    private Integer winsWithOneGoal = 0;
    private Integer winsWithTwoGoals = 0;
    private Integer winsWithMoreGoals = 0;
    private Integer lossesWithOneGoal = 0;
    private Integer lossesWithTwoGoals = 0;
    private Integer lossesWithMoreGoals = 0;
    private Integer goalsFor = 0;
    private Integer goalsAgainst = 0;
    private List<Team> opponents = new ArrayList<Team>();

    public Integer getLossesWithMoreGoals() {
        return lossesWithMoreGoals;
    }

    public void setLossesWithMoreGoals(Integer lossesWithMoreGoals) {
        this.lossesWithMoreGoals = lossesWithMoreGoals;
    }

    public Integer getWinsWithOneGoal() {
        return winsWithOneGoal;
    }

    public void setWinsWithOneGoal(Integer winsWithOneGoal) {
        this.winsWithOneGoal = winsWithOneGoal;
    }

    public Integer getWinsWithTwoGoals() {
        return winsWithTwoGoals;
    }

    public void setWinsWithTwoGoals(Integer winsWithTwoGoals) {
        this.winsWithTwoGoals = winsWithTwoGoals;
    }

    public Integer getWinsWithMoreGoals() {
        return winsWithMoreGoals;
    }

    public void setWinsWithMoreGoals(Integer winsWithMoreGoals) {
        this.winsWithMoreGoals = winsWithMoreGoals;
    }

    public Integer getLossesWithOneGoal() {
        return lossesWithOneGoal;
    }

    public void setLossesWithOneGoal(Integer lossesWithOneGoal) {
        this.lossesWithOneGoal = lossesWithOneGoal;
    }

    public Integer getLossesWithTwoGoals() {
        return lossesWithTwoGoals;
    }

    public void setLossesWithTwoGoals(Integer lossesWithTwoGoals) {
        this.lossesWithTwoGoals = lossesWithTwoGoals;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public List<Team> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Team> opponents) {
        this.opponents = opponents;
    }

    public static MarginStats getAllStats(MarginStats homeGames, MarginStats visitorGames) {
        MarginStats allGames = new MarginStats();

        allGames.setGameCount(homeGames.getGameCount() + visitorGames.getGameCount());
        allGames.setWins(homeGames.getWins() + visitorGames.getWins());
        allGames.setLosses(homeGames.getLosses() + visitorGames.getLosses());
        allGames.setTies(homeGames.getTies() + visitorGames.getTies());
        allGames.setWinsWithOneGoal(homeGames.getWinsWithOneGoal() + visitorGames.getWinsWithOneGoal());
        allGames.setWinsWithTwoGoals(homeGames.getWinsWithTwoGoals() + visitorGames.getWinsWithTwoGoals());
        allGames.setWinsWithMoreGoals(homeGames.getWinsWithMoreGoals() + visitorGames.getWinsWithMoreGoals());
        allGames.setLossesWithOneGoal(homeGames.getLossesWithOneGoal() + visitorGames.getLossesWithOneGoal());
        allGames.setLossesWithTwoGoals(homeGames.getLossesWithTwoGoals() + visitorGames.getLossesWithTwoGoals());
        allGames.setLossesWithMoreGoals(homeGames.getLossesWithMoreGoals() + visitorGames.getLossesWithMoreGoals());
        allGames.setGoalsFor(homeGames.getGoalsFor() + visitorGames.getGoalsFor());
        allGames.setGoalsAgainst(homeGames.getGoalsAgainst() + visitorGames.getGoalsAgainst());
        allGames.setOpponents(Stream
                                .concat(homeGames.getOpponents().stream(), visitorGames.getOpponents().stream())
                                .collect(Collectors.toList()));

        return allGames;
    }

    public void increaseOneGoalWins() { this.winsWithOneGoal++;}
    public void increaseTwoGoalWins() { this.winsWithTwoGoals++;}
    public void increaseMoreGoalWins() { this.winsWithMoreGoals++;}
    public void increaseOneGoalLosses() { this.lossesWithOneGoal++;}
    public void increaseTwoGoalLosses() { this.lossesWithTwoGoals++;}
    public void increaseMoreGoalLosses() { this.lossesWithMoreGoals++;}
    public void increaseGoalsFor(Integer goalsFor) { this.goalsFor += goalsFor; }
    public void increaseGoalsAgainst(Integer goalsAgainst) { this.goalsAgainst += goalsAgainst; }
    public void addOpponent(Team team) { this.opponents.add(team);}

    @Override
    public String toString() {
        return "MarginStats{" +
                "winsWithOneGoal=" + winsWithOneGoal +
                ", winsWithTwoGoals=" + winsWithTwoGoals +
                ", winsWithMoreGoals=" + winsWithMoreGoals +
                ", lossesWithOneGoal=" + lossesWithOneGoal +
                ", lossesWithTwoGoals=" + lossesWithTwoGoals +
                ", lossesWithMoreGoals=" + lossesWithMoreGoals +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", opponents=" + opponents +
                '}';
    }
}

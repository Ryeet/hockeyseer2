package fi.hockeyseer.service.data;

/**
 * Created by LickiLicki on 17-Sep-17.
 */
public class MarginStats extends BasicStats{

    private Integer winsWithOneGoal = 0;
    private Integer winsWithTwoGoals = 0;
    private Integer winsWithMoreGoals = 0;
    private Integer lossesWithOneGoal = 0;
    private Integer lossesWithTwoGoals = 0;
    private Integer lossesWithMoreGoals = 0;


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

        return allGames;
    }

    public void increaseOneGoalWins() { this.winsWithOneGoal++;}
    public void increaseTwoGoalWins() { this.winsWithTwoGoals++;}
    public void increaseMoreGoalWins() { this.winsWithMoreGoals++;}
    public void increaseOneGoalLosses() { this.lossesWithOneGoal++;}
    public void increaseTwoGoalLosses() { this.lossesWithTwoGoals++;}
    public void increaseMoreGoalLosses() { this.lossesWithMoreGoals++;}

    @Override
    public String toString() {
        return "MarginStats{" +
                "winsWithOneGoal=" + winsWithOneGoal +
                ", winsWithTwoGoals=" + winsWithTwoGoals +
                ", winsWithMoreGoals=" + winsWithMoreGoals +
                ", lossesWithOneGoal=" + lossesWithOneGoal +
                ", lossesWithTwoGoals=" + lossesWithTwoGoals +
                ", lossesWithMoreGoals=" + lossesWithMoreGoals +
                '}';
    }
}

package fi.hockeyseer.service.data;

/**
 * Created by LickiLicki on 02-Sep-17.
 */
public class BasicStats {


    private Integer gameCount = 0;
    private Integer wins = 0;
    private Integer losses = 0;
    private Integer ties = 0;


    public Integer getGameCount() {
        return gameCount;
    }

    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getTies() {
        return ties;
    }

    public void setTies(Integer ties) {
        this.ties = ties;
    }

    public void increaseGameCount() {this.gameCount++;}
    public void increaseWins() { this.wins++;}
    public void increaseLosses() { this.losses++;}
    public void increaseTies() { this.ties++;}

    @Override
    public String toString() {
        return "BasicStats{" +
                "gameCount=" + gameCount +
                ", wins=" + wins +
                ", losses=" + losses +
                ", ties=" + ties +
                '}';
    }
}

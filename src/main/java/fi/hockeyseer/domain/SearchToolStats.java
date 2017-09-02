package fi.hockeyseer.domain;

/**
 * Created by LickiLicki on 02-Sep-17.
 */
public class SearchToolStats {

    private long win = 0;
    private long tie;
    private long loss;

    public long getWin() {
        return win;
    }

    public void setWin(long win) {
        this.win = win;
    }

    public long getTie() {
        return tie;
    }

    public void setTie(long tie) {
        this.tie = tie;
    }

    public long getLoss() {
        return loss;
    }

    public void setLoss(long loss) {
        this.loss = loss;
    }

    @Override
    public String toString() {
        return "SearchToolStats{" +
                "win=" + win +
                ", tie=" + tie +
                ", loss=" + loss +
                '}';
    }
}

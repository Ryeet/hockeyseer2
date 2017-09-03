package fi.hockeyseer.domain;

/**
 * Created by LickiLicki on 02-Sep-17.
 */
public class SearchToolStats {

    private long gameCount;
    private long win;
    private long tie;
    private long loss;

    private long winMargin1;
    private long winMargin2;
    private long winMarginMore;

    private long lossMargin1;
    private long lossMargin2;
    private long lossMarginMore;

    public long getGameCount() {
        return gameCount;
    }

    public void addGameCount() {
        this.gameCount ++;
    }

    public long getWin() {
        return win;
    }

    public void addWin() {
        this.win ++;
    }

    public long getTie() {
        return tie;
    }

    public void addTie() {
        this.tie ++;
    }

    public long getLoss() {
        return loss;
    }

    public void addLoss() {
        this.loss ++;
    }

    public long getWinMargin1() {
        return winMargin1;
    }

    public void addWinMargin1() {
        this.winMargin1 ++;
    }

    public long getWinMargin2() {
        return winMargin2;
    }

    public void addWinMargin2() {
        this.winMargin2 ++;
    }

    public long getWinMarginMore() {
        return winMarginMore;
    }

    public void addWinMarginMore() {
        this.winMarginMore ++;
    }

    public long getLossMargin1() {
        return lossMargin1;
    }

    public void addLossMargin1() {
        this.lossMargin1 ++;
    }

    public long getLossMargin2() {
        return lossMargin2;
    }

    public void addLossMargin2() {
        this.lossMargin2 ++;
    }

    public long getLossMarginMore() {
        return lossMarginMore;
    }

    public void addLossMarginMore() {
        this.lossMarginMore ++;
    }

    @Override
    public String toString() {
        return "SearchToolStats{" +
                "gameCount=" + gameCount +
                ", win=" + win +
                ", tie=" + tie +
                ", loss=" + loss +
                ", winMargin1=" + winMargin1 +
                ", winMargin2=" + winMargin2 +
                ", winMarginMore=" + winMarginMore +
                ", lossMargin1=" + lossMargin1 +
                ", lossMargin2=" + lossMargin2 +
                ", lossMarginMore=" + lossMarginMore +
                '}';
    }
}

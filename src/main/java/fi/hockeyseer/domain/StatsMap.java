package fi.hockeyseer.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LickiLicki on 02-Sep-17.
 */
public class StatsMap {

    private Map<String, Long> basicStats = new HashMap<String, Long>();

    public Map<String, Long> getBasicStats() {
        return basicStats;
    }

    public void setBasicStats(Map<String, Long> basicStats) {
        this.basicStats = basicStats;
    }

    @Override
    public String toString() {
        return "StatsMap{" +
                "basicStats=" + basicStats +
                '}';
    }

    public StatsMap()
    {
        this.basicStats.put("gameCount", 0L);
        this.basicStats.put("win", 0L);
        this.basicStats.put("tie", 0L);
        this.basicStats.put("loss", 0L);
        this.basicStats.put("winMargin1", 0L);
        this.basicStats.put("winMargin2", 0L);
        this.basicStats.put("winMarginMore", 0L);
        this.basicStats.put("lossMargin1", 0L);
        this.basicStats.put("lossMargin2", 0L);
        this.basicStats.put("lossMarginMore", 0L);
    }
}

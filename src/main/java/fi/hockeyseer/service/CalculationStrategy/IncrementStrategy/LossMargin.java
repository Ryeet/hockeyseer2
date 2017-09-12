package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.domain.StatsMap;

public class LossMargin implements IncrementStrategy {

    private final String LOSS_MARGIN_1 = "lossMargin1";
    private final String LOSS_MARGIN_2 = "lossMargin2";
    private final String LOSS_MARGIN_MORE = "lossMarginMore";

    @Override
    public StatsMap incrementStats(StatsMap map, Integer difference) {

        switch (difference) {
            case 1:
                mergeMap(map, LOSS_MARGIN_1);
                break;
            case 2:
                mergeMap(map, LOSS_MARGIN_2);
                break;
            default:
                mergeMap(map, LOSS_MARGIN_MORE);
                break;
        }

        return map;
    }

    private StatsMap mergeMap(StatsMap map, String key){
         map.getBasicStats().merge(key, 1L, Long::sum);
         return map;
    }
}

package fi.hockeyseer.service.CalculationStrategy.IncrementStrategy;

import fi.hockeyseer.domain.StatsMap;

public class WinMargin implements IncrementStrategy {

    private final String WIN_MARGIN_1 = "winMargin1";
    private final String WIN_MARGIN_2 = "winMargin2";
    private final String WIN_MARGIN_MORE = "winMarginMore";

    @Override
    public StatsMap incrementStats(StatsMap map, Integer difference) {

        switch (difference) {
            case 1:
                mergeMap(map, WIN_MARGIN_1 );
                break;
            case 2:
                mergeMap(map, WIN_MARGIN_2 );
                break;
            default:
                mergeMap(map, WIN_MARGIN_MORE );
                break;
        }


        return map;
    }

    private StatsMap mergeMap(StatsMap map, String key){
        map.getBasicStats().merge(key, 1L, Long::sum);
        return map;
    }
}

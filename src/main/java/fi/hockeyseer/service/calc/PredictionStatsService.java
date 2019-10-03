package fi.hockeyseer.service.calc;

import fi.hockeyseer.service.calc.stats.basic.PredictionStats;
import fi.hockeyseer.service.calc.stats.team.TeamStats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

@Service
public class PredictionStatsService
{
    public enum COEFFICIENT
    {
        AWAY_WIN_COEFFICIENT,
        AWAY_LOSS_COEFFICIENT,
        HOME_WIN_COEFFICIENT,
        HOME_LOSS_COEFFICIENT,
        WIN_COEFFICIENT,
        AWAY_SCORE_COEFFICIENT,
        AWAY_DEF_SUCK_COEFFICIENT,
        HOME_SCORE_COEFFICIENT,
        HOME_DEF_SUCK_COEFFICIENT,
        SUCK_COEFFICIENT
    }

    private Map<COEFFICIENT, BigDecimal> homeWinPredictionConstants = new HashMap<COEFFICIENT, BigDecimal>()
    {{
        put(COEFFICIENT.AWAY_WIN_COEFFICIENT, new BigDecimal(-0.033131204));
        put(COEFFICIENT.AWAY_LOSS_COEFFICIENT, new BigDecimal(0.145591488));
        put(COEFFICIENT.HOME_WIN_COEFFICIENT, new BigDecimal(0.031513936));
        put(COEFFICIENT.HOME_LOSS_COEFFICIENT, new BigDecimal(-0.121282798));
        put(COEFFICIENT.WIN_COEFFICIENT, new BigDecimal(0.40263939));
        put(COEFFICIENT.AWAY_SCORE_COEFFICIENT, new BigDecimal(-0.109896428));
        put(COEFFICIENT.AWAY_DEF_SUCK_COEFFICIENT, new BigDecimal(0.316140625));
        put(COEFFICIENT.HOME_SCORE_COEFFICIENT, new BigDecimal(0.219488752));
        put(COEFFICIENT.HOME_DEF_SUCK_COEFFICIENT, new BigDecimal(-0.185809625));
        put(COEFFICIENT.SUCK_COEFFICIENT, new BigDecimal(0.184821001));
    }};

    private Map<COEFFICIENT, BigDecimal> tiePredictionConstants = new HashMap<COEFFICIENT, BigDecimal>()
    {{
        put(COEFFICIENT.AWAY_WIN_COEFFICIENT, new BigDecimal(-0.015475414));
        put(COEFFICIENT.AWAY_LOSS_COEFFICIENT, new BigDecimal(0.068789805));
        put(COEFFICIENT.HOME_WIN_COEFFICIENT, new BigDecimal(0.033775734));
        put(COEFFICIENT.HOME_LOSS_COEFFICIENT, new BigDecimal(0.053658787));
        put(COEFFICIENT.WIN_COEFFICIENT, new BigDecimal(0.231098205));
        put(COEFFICIENT.AWAY_SCORE_COEFFICIENT, new BigDecimal(0.002341145));
        put(COEFFICIENT.AWAY_DEF_SUCK_COEFFICIENT, new BigDecimal(-0.090241787));
        put(COEFFICIENT.HOME_SCORE_COEFFICIENT, new BigDecimal(-0.06817208));
        put(COEFFICIENT.HOME_DEF_SUCK_COEFFICIENT, new BigDecimal(-0.007005932));
        put(COEFFICIENT.SUCK_COEFFICIENT, new BigDecimal(0.39750542));
    }};

    private Map<COEFFICIENT, BigDecimal> awayWinPredictionConstants = new HashMap<COEFFICIENT, BigDecimal>()
    {{
        put(COEFFICIENT.AWAY_WIN_COEFFICIENT, new BigDecimal(0.048606618));
        put(COEFFICIENT.AWAY_LOSS_COEFFICIENT, new BigDecimal(-0.076801683));
        put(COEFFICIENT.HOME_WIN_COEFFICIENT, new BigDecimal(-0.06528967));
        put(COEFFICIENT.HOME_LOSS_COEFFICIENT, new BigDecimal(0.067624011));
        put(COEFFICIENT.WIN_COEFFICIENT, new BigDecimal(0.366262405));
        put(COEFFICIENT.AWAY_SCORE_COEFFICIENT, new BigDecimal(0.107555283));
        put(COEFFICIENT.AWAY_DEF_SUCK_COEFFICIENT, new BigDecimal(-0.225898838));
        put(COEFFICIENT.HOME_SCORE_COEFFICIENT, new BigDecimal(-0.151316672));
        put(COEFFICIENT.HOME_DEF_SUCK_COEFFICIENT, new BigDecimal(0.192815557));
        put(COEFFICIENT.SUCK_COEFFICIENT, new BigDecimal(0.417673578));
    }};

    public PredictionStats calculatePredictionStats(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        PredictionStats predictionStats = new PredictionStats();

        BigDecimal homeWinHelper = BigDecimal.valueOf(2).multiply(calculateHomeWinVariable(homeTeamStats, awayTeamStats)).add(calculateHomeWinScoringVariable(homeTeamStats, awayTeamStats));
        BigDecimal homeWinHelper2 = homeWinHelper.divide(BigDecimal.valueOf(3), MathContext.DECIMAL32);
        predictionStats.setHomeWin(BigDecimal.valueOf(1).divide(homeWinHelper2, MathContext.DECIMAL32));

        BigDecimal tieHelper = BigDecimal.valueOf(2).multiply(calculateTieVariable(homeTeamStats, awayTeamStats)).add(calculateTieScoringVariable(homeTeamStats, awayTeamStats));
        BigDecimal tieHelper2 = tieHelper.divide(BigDecimal.valueOf(3), MathContext.DECIMAL32);
        predictionStats.setTie(BigDecimal.valueOf(1).divide(tieHelper2, MathContext.DECIMAL32));

        BigDecimal awayWinHelper = BigDecimal.valueOf(2).multiply(calculateAwayWinVariable(homeTeamStats, awayTeamStats)).add(calculateAwayWinScoringVariable(homeTeamStats, awayTeamStats));
        BigDecimal awayWinHelper2 = awayWinHelper.divide(BigDecimal.valueOf(3), MathContext.DECIMAL32);
        predictionStats.setAwayWin(BigDecimal.valueOf(1).divide(awayWinHelper2, MathContext.DECIMAL32));

        return predictionStats;
    }

    public BigDecimal calculateHomeWinVariable(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        BigDecimal homeLossTendencyHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedLossTendency().multiply(homeWinPredictionConstants.get(COEFFICIENT.HOME_LOSS_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal homeWinTendencyHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedWinTendency().multiply(homeWinPredictionConstants.get(COEFFICIENT.HOME_WIN_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayLossTendencyHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedLossTendency().multiply(homeWinPredictionConstants.get(COEFFICIENT.AWAY_LOSS_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayWinTendencyHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedWinTendency().multiply(homeWinPredictionConstants.get(COEFFICIENT.AWAY_WIN_COEFFICIENT), MathContext.DECIMAL32);

        return homeLossTendencyHelper
                .add(homeWinTendencyHelper)
                .add(awayLossTendencyHelper)
                .add(awayWinTendencyHelper)
                .add(homeWinPredictionConstants.get(COEFFICIENT.WIN_COEFFICIENT));
    }

    public BigDecimal calculateTieVariable(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        BigDecimal homeLossTendencyHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedLossTendency().multiply(tiePredictionConstants.get(COEFFICIENT.HOME_LOSS_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal homeWinTendencyHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedWinTendency().multiply(tiePredictionConstants.get(COEFFICIENT.HOME_WIN_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayLossTendencyHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedLossTendency().multiply(tiePredictionConstants.get(COEFFICIENT.AWAY_LOSS_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayWinTendencyHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedWinTendency().multiply(tiePredictionConstants.get(COEFFICIENT.AWAY_WIN_COEFFICIENT), MathContext.DECIMAL32);

        return homeLossTendencyHelper
                .add(homeWinTendencyHelper)
                .add(awayLossTendencyHelper)
                .add(awayWinTendencyHelper)
                .add(tiePredictionConstants.get(COEFFICIENT.WIN_COEFFICIENT));
    }

    public BigDecimal calculateAwayWinVariable(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        BigDecimal homeLossTendencyHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedLossTendency().multiply(awayWinPredictionConstants.get(COEFFICIENT.HOME_LOSS_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal homeWinTendencyHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedWinTendency().multiply(awayWinPredictionConstants.get(COEFFICIENT.HOME_WIN_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayLossTendencyHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedLossTendency().multiply(awayWinPredictionConstants.get(COEFFICIENT.AWAY_LOSS_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayWinTendencyHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedWinTendency().multiply(awayWinPredictionConstants.get(COEFFICIENT.AWAY_WIN_COEFFICIENT), MathContext.DECIMAL32);

        return homeLossTendencyHelper
                .add(homeWinTendencyHelper)
                .add(awayLossTendencyHelper)
                .add(awayWinTendencyHelper)
                .add(awayWinPredictionConstants.get(COEFFICIENT.WIN_COEFFICIENT));
    }

    public BigDecimal calculateHomeWinScoringVariable(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        BigDecimal homeDefSuckHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedDefenseSuck().multiply(homeWinPredictionConstants.get(COEFFICIENT.HOME_DEF_SUCK_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal homeScorePowerHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedScorePower().multiply(homeWinPredictionConstants.get(COEFFICIENT.HOME_SCORE_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayDefSuckHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedDefenseSuck().multiply(homeWinPredictionConstants.get(COEFFICIENT.AWAY_DEF_SUCK_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayScorePowerHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedScorePower().multiply(homeWinPredictionConstants.get(COEFFICIENT.AWAY_SCORE_COEFFICIENT), MathContext.DECIMAL32);

        return homeDefSuckHelper
                .add(homeScorePowerHelper)
                .add(awayDefSuckHelper)
                .add(awayScorePowerHelper)
                .add(homeWinPredictionConstants.get(COEFFICIENT.SUCK_COEFFICIENT));
    }

    public BigDecimal calculateTieScoringVariable(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        BigDecimal homeDefSuckHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedDefenseSuck().multiply(tiePredictionConstants.get(COEFFICIENT.HOME_DEF_SUCK_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal homeScorePowerHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedScorePower().multiply(tiePredictionConstants.get(COEFFICIENT.HOME_SCORE_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayDefSuckHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedDefenseSuck().multiply(tiePredictionConstants.get(COEFFICIENT.AWAY_DEF_SUCK_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayScorePowerHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedScorePower().multiply(tiePredictionConstants.get(COEFFICIENT.AWAY_SCORE_COEFFICIENT), MathContext.DECIMAL32);

        return homeDefSuckHelper
                .add(homeScorePowerHelper)
                .add(awayDefSuckHelper)
                .add(awayScorePowerHelper)
                .add(tiePredictionConstants.get(COEFFICIENT.SUCK_COEFFICIENT));
    }

    public BigDecimal calculateAwayWinScoringVariable(TeamStats homeTeamStats, TeamStats awayTeamStats)
    {
        BigDecimal homeDefSuckHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedDefenseSuck().multiply(awayWinPredictionConstants.get(COEFFICIENT.HOME_DEF_SUCK_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal homeScorePowerHelper = homeTeamStats.getAdjustedPowersHome().getAdjustedScorePower().multiply(awayWinPredictionConstants.get(COEFFICIENT.HOME_SCORE_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayDefSuckHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedDefenseSuck().multiply(awayWinPredictionConstants.get(COEFFICIENT.AWAY_DEF_SUCK_COEFFICIENT), MathContext.DECIMAL32);
        BigDecimal awayScorePowerHelper = awayTeamStats.getAdjustedPowersVisitor().getAdjustedScorePower().multiply(awayWinPredictionConstants.get(COEFFICIENT.AWAY_SCORE_COEFFICIENT), MathContext.DECIMAL32);

        return homeDefSuckHelper
                .add(homeScorePowerHelper)
                .add(awayDefSuckHelper)
                .add(awayScorePowerHelper)
                .add(awayWinPredictionConstants.get(COEFFICIENT.SUCK_COEFFICIENT));
    }
}

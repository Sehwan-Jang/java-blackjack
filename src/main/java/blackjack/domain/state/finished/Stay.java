package blackjack.domain.state.finished;

import blackjack.domain.participant.Dealer;

import java.util.Arrays;

public class Stay extends Finished {

    @Override
    protected ResultType getResultType(Dealer dealer, int score) {
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return getResultTypeByDifference(score - dealer.getScore());
    }

    private ResultType getResultTypeByDifference(int difference) {
        return Arrays.stream(ResultType.values())
                .filter(resultType -> resultType.match(difference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 결과입니다."));
    }
}

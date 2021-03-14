package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.state.finished.BlackJackState;
import blackjack.util.GameInitializer;

public class InitState implements State {
    @Override
    public State stay() {
        throw new IllegalStateException("Stay를 결정할 수 없는 상태입니다.");
    }

    @Override
    public State update(Hand hand) {
        if (hand.unwrap().size() == GameInitializer.STARTING_CARD_COUNT) {
            return getInitialState(hand);
        }
        return this;
    }

    private State getInitialState(Hand hand) {
        if (hand.isBlackJack()) {
            return new BlackJackState();
        }
        return new Hit();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profitRate(Dealer dealer, int score) {
        return 0;
    }
}

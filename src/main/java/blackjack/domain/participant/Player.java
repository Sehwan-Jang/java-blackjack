package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Response;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player extends BlackJackParticipant{
    private static final int STARTING_CARD_COUNT = 2;

    private final String name;

    public Player(String name) {
        super();
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("공백은 이름으로 사용할 수 없습니다.");
        }
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().addCard(deck.draw());
        if (getHand().isBust()) {
            cannotDraw();
        }
    }

    public void willContinue(String input) {
        if (!Response.getHitStatus(input)) {
            cannotDraw();
        }
    }

    public ResultType match(Dealer dealer) {
        int myScore = getScore();
        int dealerScore = dealer.getScore();

        return ResultType.getResultType(myScore - dealerScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

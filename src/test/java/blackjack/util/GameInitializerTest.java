package blackjack.util;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.util.GameInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameInitializerTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = GameInitializer.initializeDeck();
    }

    @DisplayName("초기 덱 생성 검증")
    @Test
    void checkInitialDeck() {
        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @DisplayName("초기 플레이어 카드 분배 검증")
    @Test
    void checkPlayerCardDistribution() {
        Players players = GameInitializer.initializePlayers("a,b,c", deck);
        players.unwrap().forEach(player ->
                assertThat(player.getHand().unwrap().size()).isEqualTo(2));
    }

    @DisplayName("초기 딜러 카드 분배 검증")
    @Test
    void checkDealerCardDistribution() {
        Dealer dealer = GameInitializer.initializeDealer(deck);
        assertThat(dealer.getHand().unwrap().size()).isEqualTo(2);
    }
}

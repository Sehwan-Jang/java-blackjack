package blackjack.domain;

import blackjack.domain.participant.Player;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중복 검증")
    void validateDuplication() {
        assertThatThrownBy(() -> Players.valueOf("a,b,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("참가인원수 검증")
    @ValueSource(strings = {"1", "1,2,3,4,5,6,7,8,9"})
    void validatePlayerCount(String input) {
        assertThatThrownBy(() -> Players.valueOf(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 2명 이상, 8명 이하여합니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() throws InvalidNameInputException {
        Players players = new Players(Arrays.asList(TestSetUp.createWinner(), TestSetUp.createLoser(), TestSetUp.createTiePlayer()));
        GameResult gameResult = players.match(TestSetUp.createDealer());

        Map<Player, ResultType> expected = new HashMap<>();
        expected.put(TestSetUp.createWinner(), ResultType.WIN);
        expected.put(TestSetUp.createTiePlayer(), ResultType.TIE);
        expected.put(TestSetUp.createLoser(), ResultType.LOSE);

        assertThat(gameResult).isEqualTo(new GameResult(expected));
    }
}

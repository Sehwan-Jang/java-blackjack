package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("이름이 공백인 경우 검증")
    @ParameterizedTest
    @ValueSource(strings = {" ", "$", "abc!"})
    void validate(String input) {
        assertThatThrownBy(() -> new Player(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글, 영문 혹은 숫자로 1자 이상 입력해야합니다.");
    }
}
package domain.card;

import java.util.Arrays;

public enum Symbol {
	DIAMOND("다이아몬드"),
	CLOVER("클로버"),
	HEART("하트"),
	SPADE("스페이드");

	private static final String WRONG_SYMBOL_MESSAGE = "잘못된 심볼 이름입니다.";

	private final String name;

	Symbol(String name) {
		this.name = name;
	}

	public static Symbol of(String passedName) {
		return Arrays.stream(Symbol.values())
			.filter(symbol -> symbol.name.equals(passedName))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(WRONG_SYMBOL_MESSAGE));
	}

	public String getName() {
		return name;
	}
}
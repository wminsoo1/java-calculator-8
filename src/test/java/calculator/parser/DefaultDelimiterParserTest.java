package calculator.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultDelimiterParserTest {

    private DefaultDelimiterParser defaultDelimiterParser;

    @BeforeEach
    void setUp() {
        defaultDelimiterParser = new DefaultDelimiterParser();
    }

    @Test
    @DisplayName("기본 구분자 ,(쉼표)로 문자열을 올바르게 분리한다.")
    void splitByCommaDelimiter() {
        String input = "1,2,3";

        List<String> result = defaultDelimiterParser.parser(input);

        assertEquals(3, result.size());
        assertAll(
                () -> assertEquals("1", result.get(0)),
                () -> assertEquals("2", result.get(1)),
                () -> assertEquals("3", result.get(2))
        );
    }

    @Test
    @DisplayName("기본 구분자 :(콜론)으로 문자열을 올바르게 분리한다.")
    void splitByColonDelimiter() {
        String input = "1:2:3";

        List<String> result = defaultDelimiterParser.parser(input);

        assertEquals(3, result.size());
        assertAll(
                () -> assertEquals("1", result.get(0)),
                () -> assertEquals("2", result.get(1)),
                () -> assertEquals("3", result.get(2))
        );
    }

    @Test
    @DisplayName("마지막에 구분자가 있어도 비어있는 값 없이 정상 처리된다.")
    void splitStringEndingWithDelimiter() {
        String input = "1,2,3:";

        List<String> result = defaultDelimiterParser.parser(input);

        assertEquals(3, result.size());
        assertAll(
                () -> assertEquals("1", result.get(0)),
                () -> assertEquals("2", result.get(1)),
                () -> assertEquals("3", result.get(2))
        );
    }

    @Test
    @DisplayName("단일 숫자 입력 시 그대로 반환된다.")
    void singleNumberReturnsSingleElement() {
        String input = "1";

        List<String> result = defaultDelimiterParser.parser(input);

        assertEquals(1, result.size());
        assertEquals("1", result.get(0));
    }

}
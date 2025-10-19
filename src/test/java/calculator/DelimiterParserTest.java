package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DelimiterParserTest {

    private final DelimiterParser delimiterParser = new DelimiterParser();

    @Test
    @DisplayName("기본 구분자 ,(쉼표)로 문자열을 올바르게 분리한다.")
    void splitByCommaDelimiter() {
        String input = "1,2,3";

        List<String> result = delimiterParser.splitByDelimiters(input);

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

        List<String> result = delimiterParser.splitByDelimiters(input);

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

        List<String> result = delimiterParser.splitByDelimiters(input);

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

        List<String> result = delimiterParser.splitByDelimiters(input);

        assertEquals(1, result.size());
        assertEquals("1", result.get(0));
    }

    @Test
    @DisplayName("커스텀 구분자 추가 시 올바르게 분리된다.")
    void splitByCustomDelimiter() {
        String input = "1;2;3";
        delimiterParser.addDelimiter(";");

        List<String> result = delimiterParser.splitByDelimiters(input);

        assertEquals(3, result.size());
        assertAll(
                () -> assertEquals("1", result.get(0)),
                () -> assertEquals("2", result.get(1)),
                () -> assertEquals("3", result.get(2))
        );
    }

    @Test
    @DisplayName("빈값 입력 시 빈 리스트를 반환한다.")
    void emptyInputReturnsEmptyList() {
        String input = "";

        List<String> result = delimiterParser.splitByDelimiters(input);

        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("공백만 입력된 경우 공백 문자열 하나를 요소로 가진 리스트를 반환한다.")
    void whitespaceInputReturnsSingleWhitespaceElement() {
        String input = " ";

        List<String> result = delimiterParser.splitByDelimiters(input);

        assertEquals(1, result.size());
        assertEquals(" ", result.get(0));
    }

    @Test
    @DisplayName("delimiters 기본 구분자는 ,(쉼표) 와 :(콜론)이다.")
    void defaultDelimitersAreCommaAndColon() {
        Set<String> delimiters = delimiterParser.getDelimiters();

        assertEquals(2, delimiters.size());
        assertTrue(delimiters.contains(","));
        assertTrue(delimiters.contains(":"));
    }

}

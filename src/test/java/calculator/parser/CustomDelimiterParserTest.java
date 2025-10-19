package calculator.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomDelimiterParserTest {

    private CustomDelimiterParser customDelimiterParser;

    @BeforeEach
    void setUp() {
        customDelimiterParser = new CustomDelimiterParser();
    }

    @Test
    @DisplayName("커스텀 구분자로 입력된 문자열을 올바르게 분리한다.")
    void parseCustomDelimiterReturnsCorrectList() {
        String input = "//;\n1;2;3";
        List<String> result = customDelimiterParser.parser(input);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of("1", "2", "3")));
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자가 혼용되어 있어도 올바르게 분리한다.")
    void parseMixedDelimitersReturnsCorrectList() {
        String input = "//;\n1;2,3:";
        List<String> result = customDelimiterParser.parser(input);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of("1", "2", "3")));
    }

    @Test
    @DisplayName("줄바꿈(\\n)이 누락된 경우 예외를 던진다.")
    void throwExceptionWhenNewlineIsMissing() {
        String input = "//;1;2;3";

        assertThrows(IllegalArgumentException.class, () -> {
            customDelimiterParser.parser(input);
        });
    }

    @Test
    @DisplayName("커스텀 구분자가 비어 있으면 예외를 던진다.")
    void throwExceptionWhenCustomDelimiterIsEmpty() {
        String input = "//\\n1,2,3";

        assertThrows(IllegalArgumentException.class, () -> {
            customDelimiterParser.parser(input);
        });
    }

    @Test
    @DisplayName("숫자를 커스텀 구분자로 지정하면 예외를 던진다.")
    void throwExceptionWhenCustomDelimiterIsNumeric() {
        String input = "//1\\n1,2,3";

        assertThrows(IllegalArgumentException.class, () -> {
            customDelimiterParser.parser(input);
        });
    }

}
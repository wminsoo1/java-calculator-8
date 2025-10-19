package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputAnalyzerTest {

    @Test
    @DisplayName("//로 시작하고 \\n을 포함하며 커스텀 구분자가 유효하면 true를 반환한다.")
    void testHasCustomDelimiter_Valid() {
        InputAnalyzer analyzer = new InputAnalyzer("//;\\n1;2;3");

        assertTrue(analyzer.hasCustomDelimiter());
    }

    @Test
    @DisplayName("//로 시작하지 않으면 예외를 던진다.")
    void testHasCustomDelimiter_InvalidPrefix() {
        InputAnalyzer analyzer = new InputAnalyzer("/;\\n1;2;3");

        assertThrows(IllegalArgumentException.class, analyzer::hasCustomDelimiter);
    }

    @Test
    @DisplayName("줄바꿈(\\n)이 누락된 경우 예외를 던진다.")
    void testHasCustomDelimiter_MissingNewline() {
        InputAnalyzer analyzer = new InputAnalyzer("//;1;2;3");

        assertThrows(IllegalArgumentException.class, analyzer::hasCustomDelimiter);
    }

    @Test
    @DisplayName("커스텀 구분자가 비어 있으면 예외를 던진다.")
    void testHasCustomDelimiter_EmptyDelimiter() {
        InputAnalyzer analyzer = new InputAnalyzer("//\\n1,2,3");

        assertThrows(IllegalArgumentException.class, analyzer::hasCustomDelimiter);
    }

    @Test
    @DisplayName("숫자를 커스텀 구분자로 지정하면 예외를 던진다.")
    void testHasCustomDelimiter_NumericDelimiter() {
        InputAnalyzer analyzer = new InputAnalyzer("//1\\n1,2,3");

        assertThrows(IllegalArgumentException.class, analyzer::hasCustomDelimiter);
    }

    @Test
    @DisplayName("커스텀 구분자를 정확히 추출한다.")
    void testExtractCustomDelimiter() {
        InputAnalyzer analyzer = new InputAnalyzer("//;\\n1;2;3");

        String delimiter = analyzer.extractCustomDelimiter();

        assertEquals(";", delimiter);
    }

    @Test
    @DisplayName("커스텀 구분자 다음의 숫자 부분을 정확히 추출한다.")
    void testExtractNumbersSection() {
        InputAnalyzer analyzer = new InputAnalyzer("//;\\n1;2;3");

        String result = analyzer.extractNumbersSection();

        assertEquals("1;2;3", result);
    }

}
package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    @DisplayName("기본 구분자(',' 또는 ':')를 포함한 입력값을 올바르게 계산한다.")
    void calculate_basic_correctSum() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("1,2:3");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        int result = stringCalculator.calculate();

        assertEquals(6, result);
    }

    private final DelimiterParser delimiterParser = new DelimiterParser();

    @Test
    @DisplayName("커스텀 구분자를 포함한 입력값을 올바르게 계산한다.")
    void calculate_custom_correctSum() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("//;\\n1;2;3");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        int result = stringCalculator.calculate();

        assertEquals(6, result);
    }

    @Test
    @DisplayName("입력이 빈값이라면 0을 반환한다.")
    void calculate_empty_returnsZero() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        int result = stringCalculator.calculate();

        assertEquals(0, result);
    }

    @Test
    @DisplayName("숫자가 아닌 값이 포함되어 있으면 예외를 던진다.")
    void calculate_nonNumeric_throwsException() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("1,a,3");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        assertThrows(IllegalArgumentException.class, stringCalculator::calculate);
    }

    @Test
    @DisplayName("음수가 포함되어 있으면 예외를 던진다.")
    void calculate_negative_throwsException() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("1,-2,3");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        assertThrows(IllegalArgumentException.class, stringCalculator::calculate);
    }

    @Test
    @DisplayName("단일 숫자 입력 시 해당 값을 반환한다.")
    void calculate_singleNumber_returnsNumber() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("5");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        int result = stringCalculator.calculate();

        assertEquals(5, result);
    }

    @Test
    @DisplayName("마지막에 구분자가 있어도 올바르게 계산된다.")
    void calculate_trailingDelimiter_correctSum() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("1,2:3,");
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        int result = stringCalculator.calculate();

        assertEquals(6, result);
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자가 혼용되어 있어도 올바르게 계산된다.")
    void calculate_mixedDelimiters_correctSum() {
        InputAnalyzer inputAnalyzer = new InputAnalyzer("//;\\n1;2,3"); 
        StringCalculator stringCalculator = new StringCalculator(delimiterParser, inputAnalyzer);

        int result = stringCalculator.calculate();

        assertEquals(6, result);
    }

}
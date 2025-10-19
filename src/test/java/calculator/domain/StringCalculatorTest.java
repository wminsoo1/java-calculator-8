package calculator.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    @Test
    @DisplayName("올바른 숫자 목록을 받으면 합계를 반환한다.")
    void calculateReturnsCorrectSumForValidNumbers() {
        StringCalculator stringCalculator = new StringCalculator(List.of("1", "2", "3"));

        int result = stringCalculator.calculate();

        assertEquals(6, result);
    }

    @Test
    @DisplayName("단일 숫자 목록을 받으면 해당 값을 반환한다.")
    void calculateReturnsSingleNumber() {
        StringCalculator stringCalculator = new StringCalculator(List.of("5"));

        int result = stringCalculator.calculate();

        assertEquals(5, result);
    }

    @Test
    @DisplayName("빈 목록을 받으면 0을 반환한다.")
    void calculateReturnsZeroForEmptyList() {
        StringCalculator stringCalculator = new StringCalculator(List.of());

        int result = stringCalculator.calculate();

        assertEquals(0, result);
    }

    @Test
    @DisplayName("숫자가 아닌 값이 포함되어 있으면 예외를 던진다.")
    void calculateThrowsExceptionForNonNumericInput() {
        StringCalculator stringCalculator = new StringCalculator(List.of("1,a,3"));

        assertThrows(IllegalArgumentException.class, stringCalculator::calculate);
    }

    @Test
    @DisplayName("숫자 목록에 0이 포함되어 있으면 예외를 던진다.")
    void calculateThrowsExceptionForZeroInput() {
        StringCalculator stringCalculator = new StringCalculator(List.of("1", "0", "3"));

        assertThrows(IllegalArgumentException.class, stringCalculator::calculate);
    }

    @Test
    @DisplayName("음수가 포함되어 있으면 예외를 던진다.")
    void calculateThrowsExceptionForNegativeNumbers() {
        StringCalculator stringCalculator = new StringCalculator(List.of("1,-2,3"));

        assertThrows(IllegalArgumentException.class, stringCalculator::calculate);
    }

}
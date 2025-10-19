package calculator.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringCalculatorServiceTest {

    private StringCalculatorService service;

    @BeforeEach
    void setUp() {
        service = new StringCalculatorService();
    }

    @Test
    @DisplayName("null 또는 빈 문자열 입력 시 0을 반환한다.")
    void calculateReturnsZeroForNullOrEmptyInput() {
        assertEquals(0, service.calculate(null));
        assertEquals(0, service.calculate(""));
    }

    @Test
    @DisplayName("기본 구분자 입력 시 올바르게 파싱 및 계산된 합계를 반환한다.")
    void calculateDefaultDelimitersReturnsCorrectSum() {
        String input = "1,2:3";

        int result = service.calculate(input);

        assertEquals(6, result);
    }

    @Test
    @DisplayName("커스텀 구분자 입력 시 올바르게 파싱 및 계산된 합계를 반환한다.")
    void calculateCustomDelimiterReturnsCorrectSum() {
        String input = "//;\n1;2;3";

        int result = service.calculate(input);

        assertEquals(6, result);
    }

    @Test
    @DisplayName("파싱 또는 계산 과정에서 유효성 검사 실패 시 예외를 던진다.")
    void calculateThrowsExceptionOnValidationError() {
        String input = "1,-2,3";

        assertThrows(IllegalArgumentException.class, () -> service.calculate(input));
    }

}
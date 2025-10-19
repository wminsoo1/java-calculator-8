package calculator.domain;

import java.util.List;

public class StringCalculator {

    private final List<String> numbers;

    public StringCalculator(List<String> numbers) {
        this.numbers = numbers;
    }

    public int calculate() {
        int sum = 0;
        for (String number : numbers) {
            sum += parseNumber(number);
        }
        return sum;
    }

    private int parseNumber(String number) {
        validateNumeric(number);
        validatePositive(number);

        return Integer.parseInt(number);
    }

    private void validateNumeric(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + number);
        }
    }

    private void validatePositive(String number) {
        if (Integer.parseInt(number) <= 0) {
            throw new IllegalArgumentException("양수만 허용됩니다: " + number);
        }
    }

}

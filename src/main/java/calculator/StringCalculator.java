package calculator;

import java.util.List;

public class StringCalculator {

    private final DelimiterParser delimiterParser;
    private final InputAnalyzer inputAnalyzer;

    public StringCalculator(DelimiterParser delimiterParser, InputAnalyzer inputAnalyzer) {
        this.delimiterParser = delimiterParser;
        this.inputAnalyzer = inputAnalyzer;
    }

    public int calculate() {
        if (inputAnalyzer.isEmptyInput()) {
            return 0;
        }

        String input = inputAnalyzer.getInput();
        if (inputAnalyzer.hasCustomDelimiter()) {
            input = processCustomDelimiter();
        }

        List<String> numbers = delimiterParser.splitByDelimiters(input);
        return sumNumbers(numbers);
    }

    private String processCustomDelimiter() {
        String customDelimiter = inputAnalyzer.extractCustomDelimiter();

        delimiterParser.addDelimiter(customDelimiter);

        return inputAnalyzer.extractNumbersSection();
    }

    private int sumNumbers(List<String> numbers) {
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

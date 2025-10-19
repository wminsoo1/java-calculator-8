package calculator.service;

import static calculator.parser.DelimiterParserFactory.getDelimiterParser;

import calculator.domain.StringCalculator;
import calculator.parser.DelimiterParser;
import java.util.List;

public class StringCalculatorService {

    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        DelimiterParser delimiterParser = getDelimiterParser(input);
        List<String> numbers = delimiterParser.parser(input);

        StringCalculator calculator = new StringCalculator(numbers);
        return calculator.calculate();
    }

}

package calculator;

import static calculator.io.InputView.readInput;
import static calculator.io.OutputView.printResult;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = readInput();

        StringCalculator calculator = new StringCalculator(new DelimiterParser(), new InputAnalyzer(input));

        int result = calculator.calculate();

        printResult(result);

    }
}

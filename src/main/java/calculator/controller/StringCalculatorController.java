package calculator.controller;

import calculator.io.InputView;
import calculator.io.OutputView;
import calculator.service.StringCalculatorService;

public class StringCalculatorController {

    private final StringCalculatorService stringCalculatorService;

    public StringCalculatorController(StringCalculatorService stringCalculatorService) {
        this.stringCalculatorService = stringCalculatorService;
    }

    public void gameStart() {
        String input = InputView.readInput();
        int result = stringCalculatorService.calculate(input);
        OutputView.printResult(result);
    }
}

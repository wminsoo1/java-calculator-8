package calculator;

import static calculator.io.InputView.readInput;
import static calculator.io.OutputView.printResult;

import calculator.controller.StringCalculatorController;
import calculator.service.StringCalculatorService;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        StringCalculatorService stringCalculatorService = new StringCalculatorService();
        StringCalculatorController stringCalculatorController = new StringCalculatorController(stringCalculatorService);

        stringCalculatorController.gameStart();
    }
}

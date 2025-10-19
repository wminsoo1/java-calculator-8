package calculator;

public class InputAnalyzer {

    private static final int CUSTOM_DELIMITER_START_INDEX = 2;

    private final String input;

    public InputAnalyzer(String input) {
        this.input = input;
    }

    public boolean isEmptyInput() {
        return input == null || input.isEmpty();
    }

    public boolean hasCustomDelimiter() {
        validateStartsWithSlash();
        validateContainsNewLine();

        String customDelimiter = extractCustomDelimiter();
        validateCustomDelimiter(customDelimiter);

        return true;
    }

    public String extractCustomDelimiter() {
        int idx = input.indexOf("\\n");
        return input.substring(CUSTOM_DELIMITER_START_INDEX, idx);
    }

    public String extractNumbersSection() {
        int idx = input.indexOf("\\n");
        return input.substring(idx + 2);
    }

    public String getInput() {
        return input;
    }

    private void validateStartsWithSlash() {
        if (!input.startsWith("//")) {
            throw new IllegalArgumentException("잘못된 구분자 형식입니다. (시작이 // 아님)");
        }
    }

    private void validateContainsNewLine() {
        if (!input.contains("\\n")) {
            throw new IllegalArgumentException("잘못된 구분자 형식입니다. (줄바꿈 누락)");
        }
    }

    private void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter == null || customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }
        if (customDelimiter.matches("\\d+")) {
            throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다: " + customDelimiter);
        }
    }

}

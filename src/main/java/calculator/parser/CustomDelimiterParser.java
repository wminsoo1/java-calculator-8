package calculator.parser;

import java.util.List;
import java.util.regex.Pattern;

public class CustomDelimiterParser implements DelimiterParser {

    private static final String DEFAULT_DELIMITERS = ",|:";
    private static final String START_CUSTOM_DELIMITERS = "//";
    private static final String END_CUSTOM_DELIMITER = "\n";

    @Override
    public boolean canParse(String input) {
        return input.startsWith(START_CUSTOM_DELIMITERS);
    }

    @Override
    public List<String> parser(String input) {
        input = input.replace("\\n", END_CUSTOM_DELIMITER);

        validateContainsNewLine(input);
        int idx = input.indexOf(END_CUSTOM_DELIMITER);

        String customDelimiter = extractCustomDelimiter(input, idx);

        validateCustomDelimiter(customDelimiter);

        String regex = Pattern.quote(customDelimiter) + "|" + DEFAULT_DELIMITERS;
        input = extractNumbersSection(input, idx);

        return List.of(input.split(regex));
    }

    private void validateContainsNewLine(String input) {
        if (!input.contains(END_CUSTOM_DELIMITER)) {
            throw new IllegalArgumentException("잘못된 구분자 형식입니다. (줄바꿈 누락)");
        }
    }

    private String extractCustomDelimiter(String input, int idx) {
        return input.substring(START_CUSTOM_DELIMITERS.length(), idx);
    }

    private String extractNumbersSection(String input, int idx) {
        return input.substring(idx + END_CUSTOM_DELIMITER.length());
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

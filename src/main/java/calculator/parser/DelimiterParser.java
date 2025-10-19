package calculator.parser;

import java.util.List;

public interface DelimiterParser {
    boolean canParse(String input);
    List<String> parser(String input);
}

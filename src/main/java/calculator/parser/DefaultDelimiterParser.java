package calculator.parser;

import java.util.List;

public class DefaultDelimiterParser implements DelimiterParser {

    private static final String DEFAULT_DELIMITERS = ",|:";

    @Override
    public boolean canParse(String input) {
        return true;
    }

    @Override
    public List<String> parser(String input) {
        return List.of(input.split(DEFAULT_DELIMITERS));
    }

}

package calculator.parser;

import java.util.List;

public class DelimiterParserFactory {

    private static final List<DelimiterParser> delimiterParsers = List.of(
            new CustomDelimiterParser(),
            new DefaultDelimiterParser()
    );

    private DelimiterParserFactory() {
    }

    public static DelimiterParser getDelimiterParser(String input) {
        for (DelimiterParser delimiterParser : delimiterParsers) {
            if (delimiterParser.canParse(input)) {
                return delimiterParser;
            }
        }

        throw new IllegalStateException("DelimiterParser를 찾을 수 없습니다..");
    }

}

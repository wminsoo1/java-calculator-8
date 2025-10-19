package calculator.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterParserFactoryTest {

    @Test
    @DisplayName("커스텀 구분자 형식 입력 시 CustomDelimiterParser 인스턴스를 반환한다.")
    void getParserReturnsCustomParserForCustomInput() {
        String input = "//;\n1;2;3";

        DelimiterParser parser = DelimiterParserFactory.getDelimiterParser(input);

        assertInstanceOf(CustomDelimiterParser.class, parser);
    }

    @Test
    @DisplayName("기본 구분자 형식 입력 시 DefaultDelimiterParser 인스턴스를 반환한다.")
    void getParserReturnsDefaultParserForBasicInput() {
        String input = "1,2:3";

        DelimiterParser parser = DelimiterParserFactory.getDelimiterParser(input);

        assertInstanceOf(DefaultDelimiterParser.class, parser);
    }

    @Test
    @DisplayName("빈 문자열 입력 시 DefaultDelimiterParser 인스턴스를 반환한다.")
    void getParserReturnsDefaultParserForEmptyInput() {
        String input = "";

        DelimiterParser parser = DelimiterParserFactory.getDelimiterParser(input);

        assertInstanceOf(DefaultDelimiterParser.class, parser);
    }

}
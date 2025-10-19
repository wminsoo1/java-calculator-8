package calculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DelimiterParser {

    private final Set<String> delimiters = new HashSet<>(List.of(",", ":"));

    public List<String> splitByDelimiters(String input) {
        List<String> numbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            processCharacter(input.charAt(i), numbers, sb);
        }

        if (!sb.isEmpty()) {
            numbers.add(sb.toString());
        }

        return numbers;
    }

    public void addDelimiter(String delimiter) {
        delimiters.add(delimiter);
    }

    public Set<String> getDelimiters() {
        return delimiters;
    }

    private void processCharacter(char ch, List<String> numbers, StringBuilder sb) {
        String currentChar = String.valueOf(ch);

        if (!delimiters.contains(currentChar)) {
            sb.append(ch);
            return;
        }

        numbers.add(sb.toString());
        sb.setLength(0);
    }

}

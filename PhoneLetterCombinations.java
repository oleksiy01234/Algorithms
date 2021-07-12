import java.util.ArrayList;
import java.util.List;

public class PhoneLetterCombinations {
    private final char[][] mappings = {
        {},
        {},
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (!digits.isEmpty()) {
            permute("", digits, res);
        }
        return res;
    }

    private void permute(String prefix, String digits, List<String> res) {
        if (digits.isEmpty()) {
            res.add(prefix);
            return;
        }

        int firstDigit = Character.getNumericValue(digits.charAt(0));

        for (char letter : mappings[firstDigit]) {
            permute(prefix + letter, digits.substring(1), res);
        }
    }
}

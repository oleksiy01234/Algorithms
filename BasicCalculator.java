import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        int res = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int newNum = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    newNum = newNum * 10;
                    newNum += Character.digit(s.charAt(i), 10);
                    i++;
                }
                res += newNum * sign;
                i--;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                res *= stack.pop();
                res += stack.pop();
            }
        }

        return res;
    }
}

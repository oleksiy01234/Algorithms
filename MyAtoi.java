public class MyAtoi {
    // using long
    public int myAtoi(String s) {
        boolean positive = true;
        long res = 0;
        s = s.trim();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (i == 0 && (c == '+' || c == '-')) {
                positive = c == '+';
            } else {
                if (!Character.isDigit(c)) {
                    break;
                }

                int digit = Character.digit(c, 10);

                res *= 10;
                res += digit;

                if (res > Integer.MAX_VALUE) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }
        }
        return (int) (positive ? res : -res);
    }


    // not using long
    public int myAtoi2(String s) {
        boolean positive = true;
        int res = 0;
        s = s.trim();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (i == 0 && (c == '+' || c == '-')) {
                positive = c == '+';
            } else {
                if (!Character.isDigit(c)) {
                    break;
                }

                int digit = Character.digit(c, 10);
                if (outOfBounds(res, digit)) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                res *= 10;
                res += digit;
            }
        }
        return positive ? res : -res;
    }

    private boolean outOfBounds(int res, int dig) {
        return res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && dig > Integer.MAX_VALUE % 10);
    }
}

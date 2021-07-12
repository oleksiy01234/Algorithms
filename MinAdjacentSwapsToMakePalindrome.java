import java.util.HashSet;
import java.util.Set;

public class MinAdjacentSwapsToMakePalindrome {
    public static void test() {
        MinAdjacentSwapsToMakePalindrome m = new MinAdjacentSwapsToMakePalindrome();
        System.out.println(m.getNoOfSwaps("mamad"));
        System.out.println(m.getNoOfSwaps("asflkj"));
        System.out.println(m.getNoOfSwaps("aabb"));
        System.out.println(m.getNoOfSwaps("ntiin"));
    }

    /**
     * Algorithm:
     *     1. First check the given string is a jumbled/shuffled palindrome or not.
     *     2. Next have two pointers p1 at 0 and p2 at s.length - 1 and start iterating.
     *     3. If chars at both the pointers are same then just shrink the window (increase the p1 and decrease the p2).
     *        This means we don't need to swap
     *     4. or Else
     *          a. find the matching pair and swap the char to p2 index (increase swap count while swapping) and finally shrink the window.
     *          b. if not found just swap once with adjacent index and increase the swap count (do not shrink the window here)
     *     5. Print the Swap Count
     *
     * Time Complexity: O(n) to find Palindrome + [ O(n) for two pointer iteration * O(n) for checking and swapping ] so => O(n^2)
     * Space Complexity: O(n)
     *
     */
    private int getNoOfSwaps(String s) {
        if (!canPermutePalindrome(s)) {
            return -1;
        }

        int totalSwaps = 0;

        char[] chars = s.toCharArray();
        int p1 = 0, p2 = s.length() - 1;

        while (p2 > p1) {
            if (chars[p1] == chars[p2]) {
                p1++;
                p2--; //When the characters are equal move on
            } else {
                int k = p2;
                while (k > p1 && chars[k] != chars[p1]) {
                    k--;
                }

                if (k == p1) { //When no matching character found
                    swap(chars, p1, p1 + 1);
                    totalSwaps++;
                } else { //When Matching character found swap until K reaches p2 position
                    while (k < p2) {
                        swap(chars, k, k + 1);
                        totalSwaps++;
                        k++;
                    }
                    p1++;
                    p2--;
                }
            }
        }
        return totalSwaps;
    }

    private void swap(char[] a, int i1, int i2) {
        char tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    private boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }

        return set.size() <= 1;
    }

}

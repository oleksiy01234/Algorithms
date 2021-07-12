import java.util.Arrays;

public class MinStepsToEqualizePiles {
    public static void test() {
        System.out.println(new MinStepsToEqualizePiles().minSteps(new int[]{5, 2, 1}));
    }

    public int minSteps(int[] piles) {
        Arrays.sort(piles);

        int res = 0, distinctNums = 0;

        for (int i = 1; i < piles.length; i++) {
            if (piles[i] != piles[i - 1]) {
                distinctNums++;
            }
            res += distinctNums;
        }

        return res;
    }
}

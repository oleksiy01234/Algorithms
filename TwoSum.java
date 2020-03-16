import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum (Easy)
 * https://leetcode.com/problems/two-sum/
 * 
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target. You may assume that each input would have
 * exactly one solution, and you may not use the same element twice.
 * 
 * Example: a = [2, 7, 11, 15], t = 9. a[0] + a[1] = 2 + 7 = 9, so return [0, 1]
 */
public class TwoSum {
  public static void test() {
    TwoSum ts = new TwoSum();
    int[] a1 = { 2, 7, 11, 15 };
    int[] a2 = { 3, 2, 4 };
    System.out.println(Arrays.toString(ts.twoSum(a1, 9)));
    System.out.println(ts.twoSum2(a2, 6));
  }

  // approach 1: hashmap. O(n) time, O(n) space
  public int[] twoSum(int[] a, int t) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < a.length; i++) {
      int n = a[i];
      if (map.containsKey(t - n)) {
        return new int[] { map.get(t - n), i };
      }
      map.put(n, i);
    }

    return null;
  }

  // approach 2: sorting. O(n log n) time, O(log n) space (to sort)
  // doesn't return indices, because messes up the order
  public boolean twoSum2(int[] a, int t) {
    Arrays.sort(a);

    int lo = 0, hi = a.length - 1;

    while (lo < hi) {
      if (a[lo] + a[hi] < t) {
        lo++;
      } else if (a[lo] + a[hi] > t) {
        hi--;
      } else {
        return true;
      }
    }

    return false;
  }
}
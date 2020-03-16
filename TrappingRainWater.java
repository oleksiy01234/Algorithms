/**
 * 42. Trapping Rain Water (Hard)
 * https://leetcode.com/problems/trapping-rain-water/
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * [0,1,0,2,1,0,1,3,2,1,2,1] = 6 units of rain water are being trapped
 */
public class TrappingRainWater {

  public static void test() {
    TrappingRainWater t = new TrappingRainWater();
    int res = t.trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
    System.out.println(res);
  }

  public int trap(int[] a) {
    int lo = 0, hi = a.length - 1;
    int loMax = 0, hiMax = 0;
    int water = 0;

    while (lo < hi) {
      if (a[lo] < a[hi]) {
        water += Math.max(0, loMax - a[lo]);
        loMax = Math.max(loMax, a[lo]);
        lo++;
      } else {
        water += Math.max(0, hiMax - a[hi]);
        hiMax = Math.max(hiMax, a[hi]);
        hi--;
      }
    }

    return water;
  }
}
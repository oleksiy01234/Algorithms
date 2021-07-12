// Java program to find lexicographically minimum value after k swaps.
public class MinArrayWithKSwaps {

  // Modifies arr[0..n-1] to lexicographically smallest with k swaps.
  static void minimizeWithKSwaps(int[] a, int n, int k) {

    for (int i = 0; i < n - 1 && k > 0; ++i) {
      // Set the position where we want to put the smallest integer
      int pos = i;
      for (int j = i + 1; j < n; ++j) {

        // If we exceed the Max swaps then terminate the loop
        if (j - i > k) {
          break;
        }

        // Find the minimum value from i+1 to max k or n
        if (a[j] < a[pos]) {
          pos = j;
        }
      }

      // Swap the elements from Minimum position we found till now to the i index
      int temp;

      for (int j = pos; j > i; --j) {
        temp = a[j];
        a[j] = a[j - 1];
        a[j - 1] = temp;
      }

      // Set the final value after swapping pos-i elements
      k -= pos - i;
    }
  }

  public static void main(String[] args) {
    int[] arr = {7, 6, 9, 2, 1};
    int n = arr.length;
    int k = 3;

    minimizeWithKSwaps(arr, n, k);

    for (int j : arr) {
      System.out.print(j + " ");
    }
  }
}

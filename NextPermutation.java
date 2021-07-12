public class NextPermutation {
  public void nextPermutation(int[] a) {
    boolean permuted = false;

    for (int i = a.length - 1; i > 0; i--) {
      if (a[i] > a[i - 1]) {
        permuted = true;

        // find the next larger number, swap with it, reverse the rest of the array
        int minLarger = i;

        for (int k = i + 1; k < a.length; k++) {
          if (a[k] <= a[minLarger] && a[k] > a[i - 1]) {
            minLarger = k;
          }
        }

        swap(a, minLarger, i - 1);
        reverse(a, i, a.length - 1);
        break;
      }
    }

    if (!permuted) {
      reverse(a, 0, a.length - 1);
    }
  }

  private void reverse(int[] a, int start, int end) {
    while (start < end) {
      swap(a, start, end);
      start++;
      end--;
    }
  }

  private void swap(int[] a, int i1, int i2) {
    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
}

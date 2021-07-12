public class FirstMissingPositive {
  public int firstMissingPositive(int[] a) {
    // Base case.
    int contains = 0;
    for (int num : a)
      if (num == 1) {
        contains++;
        break;
      }

    if (contains == 0)
      return 1;

    // Replace negative numbers, zeros,
    // and numbers larger than n by 1s.
    // After this convertion nums will contain
    // only positive numbers.
    for (int i = 0; i < a.length; i++)
      if ((a[i] <= 0) || (a[i] > a.length))
        a[i] = 1;

    // Use index as a hash key and number sign as a presence detector.
    // For example, if nums[1] is negative that means that number `1`
    // is present in the array.
    // If nums[2] is positive - number 2 is missing.
    for (int i = 0; i < a.length; i++) {
      int num = Math.abs(a[i]);
      // If you meet number a in the array - change the sign of a-th element.
      // Be careful with duplicates : do it only once.
      if (num == a.length) {
        a[0] = -Math.abs(a[0]);
      } else {
        a[num] = -Math.abs(a[num]);
      }
    }

    // Now the index of the first positive number
    // is equal to first missing positive.
    for (int i = 1; i < a.length; i++) {
      if (a[i] > 0)
        return i;
    }

    if (a[0] > 0) {
      return a.length;
    }

    return a.length + 1;
  }
}

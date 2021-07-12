public class StringCompression {
  // 443. String Compression

  public int compress(char[] a) {
    int newIndex = 0;
    int startIndex = 0;

    for (int i = 1; i <= a.length; i++) {
      // reached a different letter or the end
      if (i == a.length || a[i] != a[startIndex]) {
        // append the count, if more than 1
        if (i - startIndex > 1) {
          String count = "" + (i - startIndex);
          for (char c : count.toCharArray()) {
            a[newIndex + 1] = c;
            newIndex++;
          }
        }

        // append the next letter, if there is one
        if (i < a.length) {
          a[newIndex + 1] = a[i];
        }

        // increment the index
        newIndex++;
        startIndex = i;
      }
    }

    return newIndex;
  }

}

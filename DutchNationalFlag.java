public class DutchNationalFlag {
    public void sortColors(int[] a) {
        int lo = 0, curr = 0, hi = a.length - 1;

        while (curr <= hi) {
            if (a[curr] == 0) {
                swap(a, curr, lo);
                curr++;
                lo++;
            } else if (a[curr] == 2) {
                swap(a, curr, hi);
                hi--;
            } else { // a[curr] is 1
                curr++;
            }
        }
    }

    private void swap(int[] a, int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    // two pass
    public void sortColors2(int[] a) {
        int zeroes = 0;
        int ones = 0;

        for (int n : a) {
            if (n == 0) {
                zeroes++;
            } else if (n == 1) {
                ones++;
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (i < zeroes) {
                a[i] = 0;
            } else if (i < zeroes + ones) {
                a[i] = 1;
            } else {
                a[i] = 2;
            }
        }

    }
}

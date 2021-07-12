public class FindCelebrity {
    // https://leetcode.com/problems/find-the-celebrity/submissions/

    private boolean knows(int i, int k) {
        return true; // this would return result from the graph
    }

    // naive n^2 approach
    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++) {
            if (iKnowsNoOne(i, n) && everyoneKnowsI(i, n)) {
                return i;
            }
        }

        return -1;
    }

    private boolean iKnowsNoOne(int i, int n) {
        for (int k = 0; k < n; k++) {
            if (i == k) {
                continue;
            }

            if (knows(i, k)) {
                return false;
            }
        }

        return true;
    }

    private boolean everyoneKnowsI(int i, int n) {
        for (int k = 0; k < n; k++) {
            if (i == k) {
                continue;
            }

            if (!knows(k, i)) {
                return false;
            }
        }

        return true;
    }

}

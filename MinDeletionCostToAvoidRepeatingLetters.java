public class MinDeletionCostToAvoidRepeatingLetters {
    public int minCost(String s, int[] costs) {
        int cost = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int costToDeleteAll = costs[i];
            int maxCostToDelete = costs[i];

            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                costToDeleteAll += costs[i + 1];
                maxCostToDelete = Math.max(maxCostToDelete, costs[i + 1]);
                i++;
            }

            cost += (costToDeleteAll - maxCostToDelete);
        }

        return cost;
    }
}

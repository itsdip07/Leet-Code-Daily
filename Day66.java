import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] gainByFactor = new int[maxFactor + 1];
        for (int d = 1; d <= maxFactor; d++) {
            if (freq[d] > 0) {
                int multiplesCount = 0;
                for (int m = d; m <= maxFactor; m += d) {
                    multiplesCount += freq[m];
                }
                gainByFactor[d] = multiplesCount - 1;
            }
        }

        // Store [price, gain] for items that provide a free item and beat 2 * minPrice
        List<long[]> boostedUnits = new ArrayList<>();
        for (int[] item : items) {
            int f = item[0];
            long p = item[1];
            int g = gainByFactor[f];
            if (g > 0 && p < 2L * minPrice) {
                boostedUnits.add(new long[]{p, g});
            }
        }

        boostedUnits.sort(Comparator.comparingLong(a -> a[0]));

        long totalItems = 0;
        long remainingBudget = budget;

        for (long[] unit : boostedUnits) {
            long p = unit[0];
            long g = unit[1];
            if (remainingBudget < p) {
                break;
            }
            long take = Math.min(g, remainingBudget / p);
            totalItems += 2L * take;
            remainingBudget -= take * p;
        }

        totalItems += remainingBudget / minPrice;
        return (int) totalItems;
    }
}

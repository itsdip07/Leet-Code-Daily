class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[j] stores the minimum cost to reach step j
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        
        // Base case
        dp[0] = 0;
        
        // Fill the DP table from step 1 to n
        for (int j = 1; j <= n; j++) {
            // Try coming from previous steps: j - 1, j - 2, j - 3
            for (int i = Math.max(0, j - 3); i < j; i++) {
                if (dp[i] != Integer.MAX_VALUE) {
                    int jumpCost = costs[j - 1] + (j - i) * (j - i);
                    dp[j] = Math.min(dp[j], dp[i] + jumpCost);
                }
            }
        }
        
        return dp[n];
    }
}

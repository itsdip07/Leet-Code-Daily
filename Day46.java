class Solution {
    public int minMoves(int[] nums) {
        // Step 1: Find the maximum element in the array
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        
        // Step 2: Calculate the total moves required to reach the max
        int moves = 0;
        for (int num : nums) {
            moves += (max - num);
        }
        
        return moves;
    }
}

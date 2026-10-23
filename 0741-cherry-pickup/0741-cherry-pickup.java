import java.util.Arrays;

public class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        
        // dp[r1][r2] stores max cherries collected when person 1 is at (r1, k - r1) 
        // and person 2 is at (r2, k - r2)
        int[][] dp = new int[n][n];
        
        // Initialize table with -1 (unreachable)
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        dp[0][0] = grid[0][0]; // Starting state at k = 0, (0, 0) and (0, 0)
        
        // Total steps k ranges from 1 to 2 * n - 2
        for (int k = 1; k <= 2 * n - 2; k++) {
            int[][] nextDp = new int[n][n];
            for (int[] row : nextDp) {
                Arrays.fill(row, -1);
            }
            
            // r1 and r2 are row coordinates for Person 1 and Person 2
            int minR = Math.max(0, k - (n - 1));
            int maxR = Math.min(n - 1, k);
            
            for (int r1 = minR; r1 <= maxR; r1++) {
                int c1 = k - r1;
                if (grid[r1][c1] == -1) continue; // Thorns
                
                for (int r2 = minR; r2 <= maxR; r2++) {
                    int c2 = k - r2;
                    if (grid[r2][c2] == -1) continue; // Thorns
                    
                    // Collect cherries at current step
                    int cherries = grid[r1][c1];
                    if (r1 != r2) {
                        cherries += grid[r2][c2];
                    }
                    
                    // Find max cherries from the 4 valid previous states at step k - 1
                    int prevMax = -1;
                    
                    // 1. Both moved Down
                    if (r1 > 0 && r2 > 0) {
                        prevMax = Math.max(prevMax, dp[r1 - 1][r2 - 1]);
                    }
                    // 2. Person 1 moved Down, Person 2 moved Right
                    if (r1 > 0 && c2 > 0) {
                        prevMax = Math.max(prevMax, dp[r1 - 1][r2]);
                    }
                    // 3. Person 1 moved Right, Person 2 moved Down
                    if (c1 > 0 && r2 > 0) {
                        prevMax = Math.max(prevMax, dp[r1][r2 - 1]);
                    }
                    // 4. Both moved Right
                    if (c1 > 0 && c2 > 0) {
                        prevMax = Math.max(prevMax, dp[r1][r2]);
                    }
                    
                    if (prevMax != -1) {
                        nextDp[r1][r2] = prevMax + cherries;
                    }
                }
            }
            dp = nextDp; // Move to next step
        }
        
        // If (n-1, n-1) is unreachable, return 0
        return Math.max(0, dp[n - 1][n - 1]);
    }
}
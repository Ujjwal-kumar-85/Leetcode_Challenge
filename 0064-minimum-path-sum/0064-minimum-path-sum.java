class Solution {
    
    int[][] dp = new int[201][201];

    int fun(int i, int j, int[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        if(i == n-1 && j == m-1){
            return grid[i][j];
        }
        if(i >= n || j >= m){
            return (int) 1e9;
        }

        if(dp[i][j] != -1) return dp[i][j];

        int right = grid[i][j] + fun(i, j+1, grid);

        int down = grid[i][j] + fun(i+1, j, grid);
        
        return dp[i][j] = Math.min(right, down);
    }
    public int minPathSum(int[][] grid) {
        for(int i=0; i < 201; i++){
            Arrays.fill(dp[i], -1);
        }
        return fun(0, 0, grid);
    }
}
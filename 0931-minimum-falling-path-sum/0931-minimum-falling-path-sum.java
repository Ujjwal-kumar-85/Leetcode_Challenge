class Solution {
    int[][] dp = new int[101][101];

    private int fun(int[][] matrix, int i, int j){
        if(i >= matrix.length || j >= matrix[0].length || j < 0) return 100000;
        
        if(dp[i][j] != -100000) return dp[i][j];

        if(i == matrix.length - 1) return matrix[i][j];

        int a = matrix[i][j] + fun(matrix, i + 1, j);
        int b = matrix[i][j] + fun(matrix, i + 1, j + 1);
        int c = matrix[i][j] + fun(matrix, i + 1, j - 1);

        return dp[i][j] = Math.min(a, Math.min(b, c));
    }
    public int minFallingPathSum(int[][] matrix) {
        for(int i = 0; i < 101; i++) Arrays.fill(dp[i], -100000); 

        int ans = Integer.MAX_VALUE;
        for(int j = 0; j < matrix.length; j++){
            ans = Math.min(ans, fun(matrix, 0, j));
        }
        return ans;
    }
}
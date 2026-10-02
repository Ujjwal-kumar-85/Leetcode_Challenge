class Solution {

    int[][][] dp;

    int fun(int i, int j, char[][] grid, int balance) {

        int n = grid.length;
        int m = grid[0].length;

        if (i >= n || j >= m)
            return 0;

        // Update balance using current cell
        if (grid[i][j] == '(')
            balance++;
        else
            balance--;

        // Invalid prefix
        if (balance < 0)
            return 0;

        // Too many '(' to ever close
        if (balance > (n + m - 1) - (i + j + 1))
            return 0;

        // Destination
        if (i == n - 1 && j == m - 1) {
            return balance == 0 ? 1 : 0;
        }

        if (dp[i][j][balance] != -1)
            return dp[i][j][balance];

        int down = fun(i + 1, j, grid, balance);
        int right = fun(i, j + 1, grid, balance);

        return dp[i][j][balance] = down | right;
    }

    public boolean hasValidPath(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int total = n + m - 1;

        if (total % 2 != 0)
            return false;

        if (grid[0][0] == ')')
            return false;

        dp = new int[n][m][total + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return fun(0, 0, grid, 0) == 1;
    }
}
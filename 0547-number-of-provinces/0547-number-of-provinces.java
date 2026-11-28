class Solution {
    void dfsTraverse(int start, int[][] isConnected, boolean[] visited){
        visited[start] = true;

        for(int i=0; i < isConnected.length; i++){
            if(isConnected[start][i] == 1 && !visited[i]){
                dfsTraverse(i, isConnected, visited);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;

        for(int i =0; i < visited.length; i++){
            if(!visited[i]){
                count++;

                dfsTraverse(i, isConnected, visited);
            }
        }
        return count;
    }
}
class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int insertions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else { // c == ')'
                if (openCount > 0) {
                    openCount--;
                } else {
                    insertions++;
                }
            }
        }

        return insertions + openCount;
    }
}
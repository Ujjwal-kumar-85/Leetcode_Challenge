class Solution {
    void fun(String str, int a, int b, List<String> ans, int n){
        if(a > n || b > n || b > a){
            return;
        }
        if(str.length() == 2 * n){
            ans.add(str);
            return;
        }
        fun(str + "(", a+1, b, ans, n);
        fun(str + ")", a, b+1, ans, n);
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        fun("", 0, 0, ans, n);
        return ans;
    }
}
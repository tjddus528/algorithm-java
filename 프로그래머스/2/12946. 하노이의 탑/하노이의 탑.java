import java.util.*;
class Solution {
    static List<int[]> order = new ArrayList<>();
    public int[][] solution(int n) {
        solve(n, 1, 3, 2);
        int[][] answer = new int[order.size()][2];
        for(int i=0; i<order.size(); i++) {
            answer[i][0] = order.get(i)[0];
            answer[i][1] = order.get(i)[1];
        }
        return answer;
    }
    
    static void solve(int n, int s, int e, int m) {
        if(n==1) {
            order.add(new int[]{s, e});
            return;
        }
        solve(n-1, s, m, e);
        order.add(new int[]{s, e});
        solve(n-1, m, e, s);
        return;
    }
}
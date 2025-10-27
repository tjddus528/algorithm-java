import java.util.*;
class Solution {
    static List<int[]> order = new ArrayList<>();
    public int[][] solution(int n) {
        solve(n, 1, 3);
        // for(int[] od: order) {
        //     System.out.print("["+od[0] + ", " + od[1]+"] ");
        // }
        int[][] answer = new int[order.size()][2];
        for(int i=0; i<order.size(); i++) {
            answer[i][0] = order.get(i)[0];
            answer[i][1] = order.get(i)[1];
        }
        System.out.println();
        return answer;
    }
    
    static void solve(int n, int a, int b) {
        int c = 2;
        if((a==1 && b==2)||(a==2 && b==1)) c=3;
        if((a==1 && b==3)||(a==3 && b==1)) c=2;
        if((a==2 && b==3)||(a==3 && b==2)) c=1;
        
        if(n==1) {
            order.add(new int[]{a, b});
            return;
        }
        if(n==2) {
            solve(1, a, c);
            order.add(new int[]{a, b});
            solve(1, c, b);
            return;
        }
        solve(n-1, a, c);
        order.add(new int[]{a, b});
        solve(n-1, c, b);
        return;
    }
}
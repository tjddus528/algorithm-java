import java.util.*;
class Solution {
    static int[] blank;
    static int answer = 0;
    public int solution(int n) {
        blank = new int[2*n];
        combi(0, 0, n);
        return answer;
    }
    
    static void combi(int now, int cnt, int n) {
        if(cnt >= n) {
            if(isPair(n)) answer++;
            return;
        }
        if(now >= 2*n) return;

        blank[now] = 1;
        combi(now+1, cnt+1, n);
        blank[now] = 0;
        combi(now+1, cnt, n);
    }
    static boolean isPair(int n) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<2*n; i++) {
            if(blank[i] == 1) stack.push(1);
            else {
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if(stack.size() == 0) return true;
        return false;
    }
}
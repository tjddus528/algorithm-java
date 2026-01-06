import java.util.*;
class Solution {
    static char[] alpha = {'x', 'A', 'E', 'I', 'O', 'U'};
    static int cnt;
    public int solution(String word) {
        int target = 0;
        for(int i=0; i<word.length(); i++) {
            for(int j=1; j<=5; j++) {
                if(word.charAt(i) == alpha[j]) {
                    target = target*10 + j;
                }
            }
        }
        dfs(0, target);
        return cnt;
    }
    static boolean dfs(int cur, int target) {
        if(String.valueOf(cur).length() > 5) return false;
        cnt++;
        for(int i=1; i<=5; i++) {
            if((cur*10 + i) != target) {
                if(dfs(cur*10 + i, target)) {
                    return true;
                }
            }
            else
                return true;
        }
        return false;
    }
}
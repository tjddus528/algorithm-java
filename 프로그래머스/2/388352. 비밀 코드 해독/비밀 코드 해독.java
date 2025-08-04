import java.util.*;
class Solution {
    static int[] output;
    static int vs;
    static int answer;
    static List<Set<Integer>> setList;
    static Set<String> visited = new HashSet<>();
    public int solution(int n, int[][] q, int[] ans) {
        setList = new ArrayList<>();
        for(int[] qq: q) {
            Set<Integer> set = new HashSet<>();
            for(int num: qq) {
                set.add(num);
            }
            setList.add(set);
        }
        output = new int[5];
        combi(n, 0, 1, ans);
        return answer;
    }
    
    static void combi(int n, int depth, int idx, int[] ans) {
        if(depth >= 5) {
            // output <-> q
            boolean possible = true;
            for(int i=0; i<setList.size(); i++) {
                int cnt=0;
                for(int j=0; j<5; j++) {
                    if(setList.get(i).contains(output[j])) {
                        cnt++;
                    }   
                }
                if(cnt != ans[i]) {
                    possible = false;
                    break;
                }
            }
            if(possible) answer++;
            return;
        }
        for(int i=idx; i<=n; i++) {
            output[depth] = i;
            combi(n, depth+1, i+1, ans);
        }
    }
}
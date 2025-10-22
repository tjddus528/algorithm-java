import java.util.*;
class Solution {
    static final int MAX = 1_000_000;
    static int[] in = new int[MAX];
    static int[] out = new int[MAX];
    static int N;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            in[b]++;
            out[a]++;
            N = Math.max(N, Math.max(a, b));
        }
        
        int total = 0;
        for(int i=1; i<=N; i++) {
            // 생성 정점 : in 없고, out 만 존재 => out이 총 그래프 개수
            if(in[i]==0 && out[i] >= 2) {
                answer[0] = i;
                total = out[i];
            }
            // 2. 막대 그래프 : in 존재, out 없음
            if(in[i]>0 && out[i]==0) answer[2]++;
            // 3. 8자 그래프 : in 2개, out 2개
            if(in[i]>=2 && out[i]>=2) answer[3]++;
        }
        // 1. 도넛 그래프 : 총 그래프 개수 - (막대 + 8자)
        answer[1] = total - (answer[2] + answer[3]);
        
        return answer;
    }
}
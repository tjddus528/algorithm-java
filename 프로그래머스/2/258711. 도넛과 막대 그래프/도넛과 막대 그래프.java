import java.util.*;
class Solution {
    static final int MAX = 1_000_000;
    static int V, E;
    static ArrayList<Integer>[] graph = new ArrayList[MAX];
    static boolean[] visited = new boolean[MAX];
    static int[] in = new int[MAX];
    static int[] out = new int[MAX];
    static boolean donut = true;
    static int idx = -1;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        E = edges.length;
        for(int i=0; i<MAX; i++) graph[i] = new ArrayList<Integer>();
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            in[b]++;
            out[a]++;
            V = Math.max(V, Math.max(a, b));
        }
        // System.out.println(V);
        // 1. 도넛 그래프 : 총 그래프 개수 - (막대 + 8자)
        // 2. 막대 그래프 : in 존재, out 없음
        // 3. 8자 그래프 : in 2개, out 2개
        // 생성 정점 : in 없고, out 만 존재 => out이 총 그래프 개수
        
        // for(int i=1; i<=V; i++) System.out.print(in[i] + " ");
        // System.out.println();
        // for(int i=1; i<=V; i++) System.out.print(out[i] + " ");
        // System.out.println();
        int total = 0;
        for(int i=1; i<=V; i++) {
            if(in[i]==0 && out[i] >= 2) {
                answer[0] = i;
                total = out[i];
            }
            if(in[i]>0 && out[i]==0) answer[2]++;
            if(in[i]>=2 && out[i]>=2) answer[3]++;
        }
        answer[1] = total - (answer[2] + answer[3]);
        
        return answer;
    }
}
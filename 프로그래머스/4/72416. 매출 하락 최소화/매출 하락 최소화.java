import java.util.*;
class Solution {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[][] DP;
    static int[] sumChild;
    public int solution(int[] sales, int[][] links) {
        N = sales.length;
        DP = new int[N+1][2];
        sumChild = new int[N+1];
        for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
        for(int[] link: links)  graph.get(link[0]).add(link[1]);
        dfs(1, sales);
        return Math.min(DP[1][0], DP[1][1]);
    }
    
    static void dfs(int node, int[] sales) {
        // 리프 노드이면 바로 매출액 저장
        if(graph.get(node).size()==0) {
            DP[node][0] = 0;
            DP[node][1] = sales[node-1];
            return;
        }
        
        int memberSales = 0;
        for(int member: graph.get(node))
            dfs(member, sales);
        
        boolean flag = false;
        int max = Integer.MAX_VALUE;
        for(int member: graph.get(node)) {
            if(DP[member][0] > DP[member][1]) {
                flag = true;
                sumChild[node] += DP[member][1];
            } else {
                sumChild[node] += DP[member][0];
            }
            max = Math.min(max, DP[member][1] - DP[member][0]);
        }
        DP[node][1] = sales[node-1] + sumChild[node];
        if (flag)
            DP[node][0] = sumChild[node];
        else 
            DP[node][0] = sumChild[node] + max;
        
    }
    
}
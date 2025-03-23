import java.util.*;
class Solution {
    static int[] node;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        node = new int[n+1];
        for(int i=1; i<=n; i++) node[i] = i;
        Arrays.sort(costs, (o1, o2) -> o1[2]-o2[2]);
        for(int[] cost: costs) {
            if(find(cost[0]) != find(cost[1])) {
                union(cost[0], cost[1]);
                answer+= cost[2];
            }
        }
        return answer;
    }
    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa<pb) node[pa] = pb;
        else node[pb] = pa;
    }
    static int find(int x) {
        if(node[x] == x) return x;
        return find(node[x]);
    }
}
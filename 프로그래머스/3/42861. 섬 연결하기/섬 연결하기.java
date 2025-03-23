import java.util.*;
class Solution {
    static int[] node;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // cost기준으로 정렬
        Arrays.sort(costs, (o1, o2) -> o1[2]-o2[2]);
        
        // 집합의 부모 노드 배열 초기화
        node = new int[n+1];
        for(int i=1; i<=n; i++) node[i] = i;
        
        // 모든 노드가 같은 부모를 가질 때까지 (즉, 모두 합집합시키기)
        for(int[] cost: costs) {
            if(find(cost[0]) != find(cost[1])) {    // 다른 집합이라면 union 연산, cost추가
                union(cost[0], cost[1]);
                answer+= cost[2];
            }
        }
        return answer;
    }
    // 두 노드가 속한 집합의 부모 노드를 통일시켜주기 -> 합집합
    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa<pb) node[pa] = pb;
        else node[pb] = pa;
    }
    // 부모 노드 찾기
    static int find(int x) {
        if(node[x] == x) return x;
        return find(node[x]);
    }
}
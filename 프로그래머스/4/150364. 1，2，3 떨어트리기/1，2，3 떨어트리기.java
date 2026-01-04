import java.util.*;
class Solution {
    static int N;
    static List<Node> tree = new ArrayList<>();
    static int[] count;
    static List<Integer> order = new ArrayList<>();
    static boolean found;
    static class Node {
        int index;
        int pass;
        List<Node> children = new ArrayList<>();
        int childCnt;
        Node(int idx) {
            this.index = idx; 
            pass = 0;
            childCnt = 0;
        }
        void addChild(Node node) {
            children.add(node);
            childCnt++;
        }
    }
    public int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        N = target.length;
        count = new int[N];
        Arrays.sort(edges, (o1, o2) -> {
            if(o1[0]==o2[0]) return o1[1]-o2[1];
            return o1[0]-o2[0];
        });
        for(int i=0; i<=N; i++) tree.add(new Node(i));
        for(int[] edge: edges) {
            tree.get(edge[0]).addChild(tree.get(edge[1]));
        }
        // 한번의 사이클에서 각 리프노드에 떨어지는 카드의 수를 카운팅
        // 한번의 사이클에서 각 리프노드에 떨어지는 순서를 저장
        // target의 범위와 리프노드의 카드 수의 범위와 일치하는지 확인 (초과하면 -1)
        while(!isEnough(target)) {
            dropCard(tree.get(1));
            if(isOver(target)) return new int[]{-1};
        }
        // 범위가 일치하면 카드를 오름차순으로 배분
        return shareCard(new int[order.size()], target);
    }
    static void dropCard(Node node) {
        if(node == null) return;
        if(node.childCnt == 0) {
            count[node.index-1]++;
            order.add(node.index);
            return;
        }
        int nth = node.pass % node.childCnt;
        node.pass++;
        dropCard(node.children.get(nth));
    }
    static boolean isEnough(int[] target) {
        for(int i=0; i<N; i++) {
            if(target[i]==0) continue;
            if(count[i]*3 < target[i]) return false;
        }
        return true;
    }
    static boolean isOver(int[] target) {
        for(int i=0; i<N; i++) {
            if(count[i] > target[i]) return true;
        }
        return false;
    }
    static int[] shareCard(int[] cards, int[] target) {
        for(int k=0; k<order.size(); k++) {
            for(int i=1; i<=3; i++) {
                if((count[order.get(k)-1]-1)*3 + i >= target[order.get(k)-1]) {
                    cards[k] = i;
                    count[order.get(k)-1]--;
                    target[order.get(k)-1] -= i;
                    break;
                }
            }
        }
        return cards;
    }
}
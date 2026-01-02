import java.util.*;
class Solution {
    static class Node {
        int value;
        int left;
        int right;
        Node(int v, int l, int r) {
            this.value = v;
            this.left = l;
            this.right = r;
        }
        @Override
        public String toString() {
            return "value: " + value + ", left: " + left + ", right: " + right;
        }
    }
    static List<Integer> pre = new ArrayList<>();
    static List<Integer> post = new ArrayList<>();
    public int[][] solution(int[][] nodeinfo) {
        List<int[]> nodeInfos = new ArrayList<>();
        for(int i=0; i<nodeinfo.length; i++) {
            nodeInfos.add(new int[]{nodeinfo[i][0], nodeinfo[i][1], i+1});
        }
        
        
        // y좌표 내림차순, x좌표 오름차순 정렬
        Collections.sort(nodeInfos, (o1, o2) -> {
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            else return o2[1]-o1[1];
        });
        
        // root, left, right
        int parent = nodeInfos.get(0)[2];
        List<int[]> left = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        for(int[] node: nodeInfos) {
            if(node[0] == nodeinfo[parent-1][0]) continue;
            else if(node[0] < nodeinfo[parent-1][0]) left.add(node);
            else right.add(node);
        }
        
        preOrder(parent, left, right, nodeinfo);
        postOrder(parent, left, right, nodeinfo);
        
        int[][] answer = new int[2][nodeinfo.length];
        for(int i=0; i<nodeinfo.length; i++) answer[0][i] = pre.get(i);
        for(int i=0; i<nodeinfo.length; i++) answer[1][i] = post.get(i);
        
        return answer;
        
    }
    
    static void preOrder(int parent, List<int[]> left, List<int[]> right, int[][] nodeinfo) {
        
        pre.add(parent);
        
        if(left.size() > 0) {
            int leftRoot = left.get(0)[2];
            List<int[]> leftleft = new ArrayList<>();
            List<int[]> leftright = new ArrayList<>();
            for(int[] node: left) {
                if(node[0] == nodeinfo[leftRoot-1][0]) continue;
                else if(node[0] < nodeinfo[leftRoot-1][0]) leftleft.add(node);
                else leftright.add(node);
            }
            preOrder(leftRoot, leftleft, leftright, nodeinfo);
        }
        
        
        if(right.size() > 0) {
            int rightRoot = right.get(0)[2];
            List<int[]> rightleft = new ArrayList<>();
            List<int[]> rightright = new ArrayList<>();
            for(int[] node: right) {
                if(node[0] == nodeinfo[rightRoot-1][0]) continue;
                else if(node[0] < nodeinfo[rightRoot-1][0]) rightleft.add(node);
                else rightright.add(node);
            }
            preOrder(rightRoot, rightleft, rightright, nodeinfo);
        }
    }
    
    static void postOrder(int parent, List<int[]> left, List<int[]> right, int[][] nodeinfo) {
        
        if(left.size() > 0) {
            int leftRoot = left.get(0)[2];
            List<int[]> leftleft = new ArrayList<>();
            List<int[]> leftright = new ArrayList<>();
            for(int[] node: left) {
                if(node[0] == nodeinfo[leftRoot-1][0]) continue;
                else if(node[0] < nodeinfo[leftRoot-1][0]) leftleft.add(node);
                else leftright.add(node);
            }
            postOrder(leftRoot, leftleft, leftright, nodeinfo);
        }
        
        
        if(right.size() > 0) {
            int rightRoot = right.get(0)[2];
            List<int[]> rightleft = new ArrayList<>();
            List<int[]> rightright = new ArrayList<>();
            for(int[] node: right) {
                if(node[0] == nodeinfo[rightRoot-1][0]) continue;
                else if(node[0] < nodeinfo[rightRoot-1][0]) rightleft.add(node);
                else rightright.add(node);
            }
            postOrder(rightRoot, rightleft, rightright, nodeinfo);
        }
        
        post.add(parent);
        
    }
}
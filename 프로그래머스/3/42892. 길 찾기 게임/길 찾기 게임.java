import java.util.*;
class Solution {
    static class Node {
        int x, y;
        int index;
        Node left;
        Node right;
        Node(int _x, int _y, int idx) {
            this.x = _x;
            this.y = _y;
            this.index = idx;
        }
    }
    static void insertNode(Node parent, Node newNode) {
        if(parent.x > newNode.x) {
            if(parent.left == null) {
                parent.left = newNode;
                return;
            }
            insertNode(parent.left, newNode);
        } else if (parent.x < newNode.x) {
            if(parent.right == null) {
                parent.right = newNode;
                return;
            }
            insertNode(parent.right, newNode);
        }
    }
    
    static List<Integer> pre = new ArrayList<>();
    static List<Integer> post = new ArrayList<>();
    public int[][] solution(int[][] nodeinfo) {
        List<int[]> nodeInfos = new ArrayList<>();
        for(int i=0; i<nodeinfo.length; i++)
            nodeInfos.add(new int[]{nodeinfo[i][0], nodeinfo[i][1], i+1});
        
        // y좌표 내림차순, x좌표 오름차순 정렬
        Collections.sort(nodeInfos, (o1, o2) -> {
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            else return o2[1]-o1[1];
        });
        Node root = new Node(nodeInfos.get(0)[0], nodeInfos.get(0)[1], nodeInfos.get(0)[2]);
        for(int i=1; i<nodeInfos.size(); i++) {
            Node node = new Node(nodeInfos.get(i)[0], nodeInfos.get(i)[1], nodeInfos.get(i)[2]);
            insertNode(root, node);
        }
        
        preOrder(root);
        postOrder(root);
        
        int[][] answer = new int[2][nodeinfo.length];
        for(int i=0; i<nodeinfo.length; i++) answer[0][i] = pre.get(i);
        for(int i=0; i<nodeinfo.length; i++) answer[1][i] = post.get(i);
        return answer;
        
    }
    
    static void preOrder(Node root) {
        if(root == null) return;
        pre.add(root.index);
        preOrder(root.left);
        preOrder(root.right);
    }
    
    static void postOrder(Node root) {
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        post.add(root.index);
    }
}
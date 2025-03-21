import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> temp = new PriorityQueue<>();
        for(String oper: operations) {
            String[] data = oper.split(" ");
            if(data[0].equals("I")) {
                min.offer(Integer.parseInt(data[1]));
            }
            else if (data[1].equals("1")) {         // 최댓값 삭제
                while(min.size()>1) {
                    temp.offer(min.poll());
                }
                min = temp;
                temp = new PriorityQueue<>();
            } else {                                // 최솟값 삭제
                min.poll();
            }
        }
        if(min.size()==0) return new int[]{0,0};
        int first = min.poll();
        int last = first;
        while(!min.isEmpty()) last = min.poll();
        return new int[]{last, first};
    }
}
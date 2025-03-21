import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for(String oper: operations) {
            String[] data = oper.split(" ");
            if(data[0].equals("I")) {
                min.offer(Integer.parseInt(data[1]));
                max.offer(Integer.parseInt(data[1]));
            }
            else if (data[1].equals("1")) {         // 최댓값 삭제
                min.remove(max.poll());
            } else {                                // 최솟값 삭제
                max.remove(min.poll());
            }
        }
        if(min.isEmpty() && max.isEmpty()) return new int[]{0,0};
        else return new int[]{max.poll(), min.poll()};
    }
}
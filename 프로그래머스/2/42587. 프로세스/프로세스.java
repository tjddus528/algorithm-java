import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<priorities.length; i++) {
            q.offer(new int[]{i, priorities[i]});
            pq.offer(priorities[i]);
        }
        int order = 1;
        while(!q.isEmpty()){
            int[] process = q.poll();
            if(process[1]==pq.peek()) {
                if(process[0]==location) return order;
                order++;
                pq.poll();
                continue;
            }
            else q.offer(process);

        }
        return answer;
    }
}
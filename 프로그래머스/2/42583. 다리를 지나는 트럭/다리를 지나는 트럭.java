import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int n = truck_weights.length;
        Queue<int[]> q = new LinkedList<>();
        int total = 0;
        int idx = 0;
        int now = 0;
        while(idx < n) {
            if(!q.isEmpty() && q.peek()[2] <= now) {
                total -= q.poll()[1];
            }
            while (q.size() < bridge_length && idx < n && total+truck_weights[idx] <= weight) {
                q.add(new int[]{idx, truck_weights[idx], now+bridge_length});
                total += truck_weights[idx];
                idx++;
            }
            now++;
        }
        while(!q.isEmpty()) {
            if(q.peek()[2] <= now) q.poll();
            now++;
        }
        return now;
    }
}
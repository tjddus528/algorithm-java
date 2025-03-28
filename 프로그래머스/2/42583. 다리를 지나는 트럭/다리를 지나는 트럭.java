import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int n = truck_weights.length;
        Queue<int[]> q = new LinkedList<>();
        int total = 0;
        int idx = 0;
        int now = 0;
        // 모든 트럭이 다리위에 올라갈 때까지 반복문
        // 트럭을 큐에 넣을 때에는 {idx, weight, 다리를 다 건너는 시간(현재시간+다리길이)}
        while(idx < n) {
            // 큐 맨앞 원소가 다리를 다 건너는 시간이 되면 poll, 총합무게에서 제외
            if(!q.isEmpty() && q.peek()[2] <= now) {
                total -= q.poll()[1];
            }
            // 동시에 다리에 오를 수 있는 조건을 만족하면 계속 추가(무게 추가, idx++)
            while (q.size() < bridge_length && idx < n && total+truck_weights[idx] <= weight) {
                q.add(new int[]{idx, truck_weights[idx], now+bridge_length});
                total += truck_weights[idx];
                idx++;
            }
            now++;
        }
        // 모든 트럭을 다리 위에 놓은 뒤에 아직 다 건너지 못한 트럭이 있다면
        while(!q.isEmpty()) {
            // 트럭이 다 건너는 시간까지 기다렸다가 poll
            if(q.peek()[2] <= now) q.poll();
            now++;
        }
        return now;
    }
}
import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        // 우선순위큐 -> 소요 시간 기준 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> o1[1]-o2[1]);
        // 기존 배열 -> 요청 시간 기준 오름차순 정렬
        Arrays.sort(jobs, (o1, o2)->o1[0]-o2[0]);
        
        int k = 0;
        int time = jobs[0][0];
        int last = jobs[jobs.length-1][0];
        int process = 0;
        
        while(true) {
            // 현재 time 이전까지 요청된 작업들 pq에 추가
            while(k<jobs.length && jobs[k][0] <= time) {
                pq.add(jobs[k]);
                k++;
            }
            // time내에 요청된 작업이 없을 때
            if (pq.isEmpty()) {
                if(k<jobs.length) time++; // 아직 작업들이 남아있다면 time을 늘려서 계속 진행
                else break;               // 남은 작업이 없으면 break;
            }
            // 현재 시점에서 처리할 작업들 중 가장 우선순위가 높은 작업 처리 
            // (time이 갱신되므로 그 사이 새로운 작업이 요청될 수 있으므로 1개만 처리하고 넘어감)
            else {
                int[] job = pq.poll();
                time += job[1];
                process += time - job[0];
            }
        }
        return process/jobs.length;
    }
    
}
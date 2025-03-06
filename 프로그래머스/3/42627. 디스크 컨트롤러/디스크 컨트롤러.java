import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        // 우선순위큐 -> 소요 시간 기준 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> o1[1]-o2[1]);
        // {
        //     if(o1[1]==o2[1]) return o1[0]-o2[0];
        //     else if (o1[1] > o2[1]) return o1[1]-o2[1];
        //     else return -1;
        //     });
        // 기존 배열 -> 요청 시간 기준 오름차순 정렬
        Arrays.sort(jobs, (o1, o2)->o1[0]-o2[0]);
        
        int k = 0;
        int time = jobs[0][0];
        int last = jobs[jobs.length-1][0];
        int process = 0;
        
        while(true) {
            // System.out.println("time:" +time);
            // 현재 time 이전까지 요청된 작업들 pq에 추가
            while(k<jobs.length && jobs[k][0] <= time) {
                pq.add(jobs[k]);
                k++;
            }
            // PriorityQueue<int[]> temp = new PriorityQueue<>(pq);
            // while(!temp.isEmpty()) {
            //     System.out.print(Arrays.toString(temp.poll()) + " ");
            // }
            // System.out.println();
            // time내에 요청된 작업이 없지만, 
            // 아직 작업들이 남아있다면 time을 늘려서 계속 진행
            // 남은 작업이 없으면 break;
            if (pq.isEmpty()) {
                if(k<jobs.length) time++;
                else break;
            }
            // 현재 시점에서 처리할 작업들이 우선순위에 따라 순차적으로 나옴
            else {
                int[] job = pq.poll();
                time += job[1];
                process += time - job[0];
            }
        }
        return process/jobs.length;
    }
    
}
import java.util.*;
class Solution {
    static PriorityQueue<int[]> pq;
    static int k;
    public int solution(int[][] jobs) {
        pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[1]>b[1]) return 1;
                else if(a[1]==b[1]) return a[0]-b[0];
                else return -1;
            }
        });
        Arrays.sort(jobs, (a, b)->a[0]-b[0]);
        int time = jobs[0][0];
        int last = jobs[jobs.length-1][0];
        System.out.println(last);
        int process = 0;
        offerNewJob(jobs, time);
        while(!pq.isEmpty()) {
            int[] job = pq.poll();
            if (job[0] <= time) {
                time += job[1];
                process += time - job[0];
            } else {
                time++;
                pq.offer(job);
            }
            // 갱신된 time 기준 새롭게 요청된 작업들 대기 큐에 넣기
            offerNewJob(jobs, time);
        }
        return process/jobs.length;
    }
    static void offerNewJob(int[][] jobs, int time) {
        while(k<jobs.length) {
            if(jobs[k][0] <= time) {
                pq.offer(jobs[k]);
                k++;
            } else {
                if(pq.isEmpty()) time++;
                else return;
            }
        }
    }
}
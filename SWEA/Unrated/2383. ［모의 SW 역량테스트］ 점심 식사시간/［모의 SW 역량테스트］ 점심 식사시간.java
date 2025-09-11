import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 점심 식사시간
public class Solution {
    static int N, arr[][], stairs[][];
    static int output[];
    static int personCnt, stairCnt;
    static ArrayList<int[]> personList;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            stairs = new int[2][2];
            output = new int[11];
            personList = new ArrayList<>();
            min = Integer.MAX_VALUE;
            personCnt = 0;
            stairCnt = 0;
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] > 1) {
                        stairs[stairCnt][0] = i;
                        stairs[stairCnt][1] = j;
                        stairCnt++;
                    }
                    else if(arr[i][j] == 1) {
                        personList.add(new int[]{i, j});
                        personCnt++;
                    }
                }
            }
            // 각 사람별로 계단1,2 중에 하나 선택
            dupPerm(0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dupPerm(int personIdx) {
        if(personIdx >= personCnt) {
            simul();
            return;
        }

        output[personIdx] = 0;
        dupPerm(personIdx+1);
        output[personIdx] = 1;
        dupPerm(personIdx+1);
    }

    static void simul() {

        int[] allMoveTime = new int[personCnt];
        int[] allStairTime = new int[personCnt];

        PriorityQueue<StairPerson> pq1 = new PriorityQueue<>();
        PriorityQueue<StairPerson> pq2 = new PriorityQueue<>();
        
        for(int i=0; i<personCnt; i++) {
            int stairIdx = output[i];
            int moveTime = Math.abs(stairs[stairIdx][0]-personList.get(i)[0]) + Math.abs(stairs[stairIdx][1]-personList.get(i)[1]);
            if(stairIdx==0) pq1.add(new StairPerson(i, moveTime));
            else            pq2.add(new StairPerson(i, moveTime));

            allMoveTime[i] = moveTime;
        }

        int curTime = 1;
        ArrayDeque<StairPerson> stair1 = new ArrayDeque<>();
        ArrayDeque<StairPerson> stair2 = new ArrayDeque<>();
        while(!pq1.isEmpty() || !pq2.isEmpty() || !stair1.isEmpty() || !stair2.isEmpty()) {

            // 계단1을 내려가는 사람들 중 다 내려간 사람이 있으면
            while(!stair1.isEmpty() && stair1.peek().arriveTime <= curTime) {
                StairPerson person = stair1.poll();
                allStairTime[person.idx] = person.arriveTime;
            }
            // 계단1에 3명이하인데, curTime기준 대기하는 사람이 있다면
            while(stair1.size() < 3 && !pq1.isEmpty() && pq1.peek().arriveTime <= curTime) {
                StairPerson person = pq1.poll();
                // int waitingTime = curTime - person.arriveTime;
                stair1.add(new StairPerson(person.idx, curTime + arr[stairs[0][0]][stairs[0][1]]));
            }

            // 계단2을 내려가는 사람들 중 다 내려간 사람이 있으면
            while(!stair2.isEmpty() && stair2.peek().arriveTime <= curTime) {
                StairPerson person = stair2.poll();
                allStairTime[person.idx] = person.arriveTime;
            }
            // 계단2에 3명이하인데, curTime기준 대기하는 사람이 있다면
            while(stair2.size() < 3 && !pq2.isEmpty() && pq2.peek().arriveTime <= curTime) {
                StairPerson person = pq2.poll();
                // int waitingTime = curTime - person.arriveTime;
                stair2.add(new StairPerson(person.idx, curTime + arr[stairs[1][0]][stairs[1][1]]));
            }
            curTime++;
        }

        min = Math.min(min, curTime);
    }

    static class StairPerson implements Comparable<StairPerson>{ 
        int idx;
        int arriveTime;

        public StairPerson(int i, int mT) {
            this.idx = i;
            this.arriveTime = mT;
        }

        @Override
        public int compareTo(StairPerson o) {
            return Integer.compare(this.arriveTime, o.arriveTime);
        }

        @Override
        public String toString() {
            return "[" + idx + ", " + arriveTime + "]";
        }
        
    }
}

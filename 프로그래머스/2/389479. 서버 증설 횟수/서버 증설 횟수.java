import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int h=0; h<players.length; h++) {
            int now = players[h];
            
            // 현재 시간 기준 끝나야 하는 서버 끝내기
            while (!pq.isEmpty() && pq.peek() < h) {
                pq.poll();
            }
            
            // 서버 개수
            int size = pq.size();
            
            // 부족한 서버 증가하기
            int needs = now/m;
            while (needs > pq.size()) {
                pq.add(h+k-1);
                answer++;
            }
            
        }
        return answer;
    }
}
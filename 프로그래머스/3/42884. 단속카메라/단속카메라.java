import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2)->o1[0]-o2[0]);
        int end = routes[0][1];
        int answer = 1;
        for(int i=1; i<routes.length; i++) {
            // 다음 차량의 경로가 안 겹칠 때
            if(end < routes[i][0]) {
                answer++;
                end = routes[i][1];
            } 
            else 
                end = Math.min(end, routes[i][1]);
            
        }
        return answer;
    }
}
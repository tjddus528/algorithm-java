import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int n = speeds.length;
        int[] days = new int[n];
        for(int i =0; i<n; i++) {
            days[i] = (int)Math.ceil((100-progresses[i])/(double)speeds[i]);
        }
        int done = days[0];
        int cnt = 1;
        for(int i=1; i<n; i++) {
            if(days[i] <= done) cnt++;
            else {
                answer.add(cnt);
                done = days[i];
                cnt = 1;
            }
        }
        if(cnt != 0) answer.add(cnt);
        int[] result = new int[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
}
import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i=0; i<priorities.length; i++) {
            q.offer(new int[]{i, priorities[i]});
        }
        Integer[] pr = Arrays.stream(priorities).boxed().toArray(Integer[]::new);
        Arrays.sort(pr, Comparator.reverseOrder());
        int order = 1;
        while(!q.isEmpty()){
            int[] process = q.poll();
            if(process[1]==pr[order-1]) {
                if(process[0]==location) return order;
                order++;
                continue;
            }
            else q.offer(process);

        }
        return answer;
    }
}
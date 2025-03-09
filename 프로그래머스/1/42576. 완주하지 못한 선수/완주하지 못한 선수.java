import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        PriorityQueue<String> pq2 = new PriorityQueue<>();
        for(String pt: participant) pq.add(pt);
        for(String cp: completion) pq2.add(cp);
        while(!pq2.isEmpty()) {
            String p = pq.poll();
            String c = pq2.poll();
            if(!p.equals(c)) {
                return p;
            }
        }
        return pq.poll();
    }
}
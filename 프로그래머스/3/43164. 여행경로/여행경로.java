import java.util.*;
class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(int i=0; i<tickets.length; i++) {
            if(map.containsKey(tickets[i][0])) {
                map.get(tickets[i][0]).offer(tickets[i][1]);
            }
            else {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.offer(tickets[i][1]);
                map.put(tickets[i][0], pq);
            }
        }
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.add("ICN");
        while(!stack.isEmpty()) {
            String depart = stack.peek();
            if(map.containsKey(depart) && !map.get(depart).isEmpty()) {
                String arrive = map.get(depart).poll();
                stack.add(arrive);
            } else {
                result.add(stack.pop());
            }
        }
        Collections.reverse(result);
        return result.toArray(new String[result.size()]);
    }
}

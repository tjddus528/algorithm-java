import java.util.*;
class Solution {
    static List<List<Integer>> customers;
    public int solution(String[][] book_time) {
        int answer = 0;
        customers = new ArrayList<>();
        for(int i=0; i<book_time.length; i++) customers.add(new ArrayList<>());
        // customers = new int[book_time.length][book_time[0].length];
        // book_time 시작시간 기준 오름차순 정렬 
        int i=0;
        for(String[] book: book_time) {
            String[] starts = book[0].split(":");
            String[] ends = book[1].split(":");
            int start_time = Integer.parseInt(starts[0])*60+Integer.parseInt(starts[1]);
            int end_time = Integer.parseInt(ends[0])*60+Integer.parseInt(ends[1]);
            customers.get(i).add(start_time);
            customers.get(i).add(end_time);
            i++;
        }
        Collections.sort(customers, (o1, o2) -> o1.get(0)-o2.get(0));
        // System.out.println(customers);    
        // 우선순위 큐 : 종료시간+10분
        PriorityQueue<Integer> end = new PriorityQueue<>();
        end.offer(customers.get(0).get(1)+10);
        int idx = 1;
        answer = 1;
        while(idx<book_time.length && !end.isEmpty()) {
            int s = customers.get(idx).get(0);
            if(!end.isEmpty() && end.peek() <= s) end.poll();
            end.offer(customers.get(idx).get(1)+10);
            answer = Math.max(answer, end.size());
            idx++;
        }
        
        return answer;
    }
}
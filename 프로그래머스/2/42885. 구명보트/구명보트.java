import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 양방향 큐 => 덱 : 오름차순 정렬
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        Arrays.sort(people);
        for(int w: people) {
            deq.offer(w);
        }
        // 양쪽에서 하나씩 (큰수 하나/ 큰수+작은수)
        Stack<Integer> stack = new Stack<>();
        while(!deq.isEmpty()) {
            stack.add(deq.pollLast());
            answer++;
            if (deq.isEmpty()) {
                stack.pop();
                break;
            }
            if (stack.pop() + deq.peekFirst() <= limit) {
                deq.pollFirst();
            }
        }
        return answer;
    }
}
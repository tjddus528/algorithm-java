import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        Arrays.sort(people);
        for(int w: people) {
            deq.offer(w);
        }
        Stack<Integer> stack = new Stack<>();
        while(!deq.isEmpty()) {
            stack.add(deq.pollLast());
            if (deq.isEmpty()) {
                stack.pop();
                answer++;
                break;
            }
            if (stack.pop() + deq.peekFirst() <= limit) {
                deq.pollFirst();
            }
            answer++;
        }
        return answer;
    }
}
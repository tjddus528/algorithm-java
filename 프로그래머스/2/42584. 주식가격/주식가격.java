import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[prices.length];
        stack.add(0);
        for(int i=1; i<prices.length; i++) {
            // 값이 감소할 때
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int time = i-stack.peek();
                result[stack.pop()] = time;
            }
            // 값이 증가할 때
            stack.add(i);

            
        }
        while(!stack.isEmpty()){
            int idx = stack.pop();
            result[idx] = prices.length-idx-1;
        }
        return result;
    }
}
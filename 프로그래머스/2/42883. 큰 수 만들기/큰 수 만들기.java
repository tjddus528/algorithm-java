import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<number.length(); i++) {
            int n = (int)(number.charAt(i)) - 48;
            while(k>0 && !stack.isEmpty() && stack.peek() < n) {
                stack.pop();
                k--;
            }
            if(stack.isEmpty() || stack.peek() >= n || k==0) stack.add(n);
        }
        while(k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        Stack<Integer> temp = new Stack<>();
        while(!stack.isEmpty()){
            temp.add(stack.pop());
        }
        while(!temp.isEmpty()) {
            answer += String.valueOf(temp.pop());
        }
        return answer;
    }
}
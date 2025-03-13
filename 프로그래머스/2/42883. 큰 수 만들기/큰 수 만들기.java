import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<number.length(); i++) {
            char n = number.charAt(i);
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
        StringBuilder sb = new StringBuilder();
        for(char c: stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
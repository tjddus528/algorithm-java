import java.util.*;
class Solution {
    static char[] open = {'[', '(', '{'};
    static char[] close = {']', ')', '}'};
    static char[] temp;
    static Stack<Integer> stacks = new Stack<>();
    public int solution(String s) {
        int len = s.length();
        temp = new char[len];
        s += s;
        int answer = 0;
        for(int x=0; x<len; x++) {
            for(int i=0; i<len; i++) temp[i] = s.charAt(i+x);
            stacks.clear();
            if(isCorrect(len, temp)) answer++;
        }
        
        return answer;
    }
    static boolean isCorrect(int len, char[] str) {
        for(int i=0; i<len; i++) {
            for(int j=0; j<3; j++) {
                if(str[i] == open[j]) stacks.add(j);
                else if (str[i] == close[j]) {
                    if(stacks.size() > 0 && stacks.peek() == j) stacks.pop();
                    else return false;
                }
            }
        }
        for(int j=0; j<3; j++) {
            if(stacks.size() > 0) return false;
        }
        return true;
    }
}
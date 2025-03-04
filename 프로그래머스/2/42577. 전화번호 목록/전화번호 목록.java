import java.util.*;
class Solution {
    static int n;
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        n = phone_book.length;
        Arrays.sort(phone_book);
        for(int i=0; i<n-1; i++) {
            answer = prefix(phone_book[i], phone_book[i+1]);
            if(!answer) return answer;
        }
        return answer;
    }
    static boolean prefix(String pre, String word) {
        if(pre.length() <= word.length() && pre.charAt(0)==word.charAt(0)) {
            if(pre.equals(word.substring(0, pre.length()))) {
                return false;
            }
        }
        return true;
    }
}
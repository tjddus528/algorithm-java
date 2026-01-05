import java.util.*;
class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Arrays.sort(words);
        for(int i=0; i<words.length; i++) {
            int res = 0;
            // 앞단어와 비교
            if(i > 0) {
                int minLen = Math.min(words[i-1].length(), words[i].length());
                int cnt = 0;
                for(int j=0; j< minLen; j++) {
                    if(words[i].charAt(j) == words[i-1].charAt(j)) cnt++;
                    else break;
                }
                res = Math.max(res, Math.min(cnt+1, words[i].length()));
            }
            // 뒷단어와 비교
            if(i < words.length-1) {
                int minLen = Math.min(words[i].length(), words[i+1].length());
                int cnt = 0;
                for(int j=0; j< minLen; j++) {
                    if(words[i].charAt(j) == words[i+1].charAt(j)) cnt++;
                    else break;
                }
                res = Math.max(res, Math.min(cnt+1, words[i].length()));
            }
            answer += res;
        }
        return answer;
    }
}
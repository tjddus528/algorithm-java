import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int n = citations.length;
        int idx = 0;
        for(int h=0; h<=citations[n-1]; h++) {
            while(citations[idx] < h) idx++;
            if(n - idx >= h && idx <= h) answer = h;
            
        }
        return answer;
    }
}
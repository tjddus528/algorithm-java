import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        int[] cnt = new int[n+1];
        for(int i=1; i<=n; i++) cnt[i] = 1;
        for(int r: reserve) cnt[r]++;
        for(int l: lost) cnt[l]--;
        for(int l: lost) { 
            if(cnt[l]==0) {
                if(l-1>0 && cnt[l-1]>1) {
                    cnt[l-1]--;
                    cnt[l]++;
                }
                else if(l+1<=n &&cnt[l+1]>1) {
                    cnt[l+1]--;
                    cnt[l]++;
                }
            }
        }
        for(int i=1; i<=n; i++) 
            if(cnt[i]>0) answer++;
        return answer;
    }
}
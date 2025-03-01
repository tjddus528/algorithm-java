import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        // times 정렬
        Arrays.sort(times);
        
        // 가능한 시간대의 범위 [최소심사시간 ~ 최소심사시간*N]
        long left = 1;
        long right = (long)times[0]*n;
        
        // 범위 중 n명이 모두 심사를 받을 수 있는 시간대의 최소를 찾기
        while(left < right) {
            long mid = (left+ right) / 2;
            // System.out.println(mid+":"+possible(mid, n, times));
            if(possible(mid, n, times)) right = mid;
            else left = mid+1;
        }
        return right;
    }
    static boolean possible(long t, int n, int[] times) {
        long total = 0;
        for(int time: times) {
            total+=t/time;
        }
        if(total >= n) return true;
        return false;
    }
}
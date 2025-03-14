import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 0;
        int right = distance;
        Arrays.sort(rocks);
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = remove(mid, distance, rocks);
            if(cnt > n) right = mid-1;
            else left = mid+1;
        }
        return right;
    }
    static int remove(int min, int distance, int[] rocks) {
        int cnt = 0;
        int prev = 0;
        for(int rock: rocks) {
            if(rock - prev < min) cnt++;
            else prev = rock;
        }
        if(distance - prev < min) cnt++;
        return cnt;
    }
}

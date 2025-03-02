import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int w = 0;
        int h = 0;
        for(int[] size: sizes) {
            // 각 명함을 정렬 (무조건 가로 < 세로 되도록)
            Arrays.sort(size);
            // 가로 중 최대 길이, 세로 중 최대 길이
            w = Math.max(size[0], w);
            h = Math.max(size[1], h);
        }
        return w*h;
    }
}
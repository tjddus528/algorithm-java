class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        for(int i=0; i<len; i++) {
            answer += changeCount('A', name.charAt(i));
        }
        
        int moveMin = len+1;
        for(int i=0; i<len; i++) {
            int next = i+1;
            // 다음 칸이 A의 연속구간일 경우, 해당 구간이 끝나는 인덱스까지 이동
            while(next < len && name.charAt(next)=='A') {
                next++;
            }
            int rightBack = 2*i + (len-next);
            int leftBack = 2*(len-next) + i;
            moveMin = Math.min(moveMin, Math.min(rightBack, leftBack));
        }
        
        return answer+moveMin;
    }
    
    static int changeCount(char start, char target) {
        int a = Math.abs(start-target);
        int b = Math.abs(26-a);
        return Math.min(a, b);
    }
}
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 1;
        
        int start = 0;
        int end = 0;
        for(int diff: diffs) {
            start = Math.min(diff, start);
            end = Math.max(diff, end);
        }
        // System.out.println(start);
        // System.out.println(end);
        
        while(start <= end) {
            int mid = (start + end) / 2;
            // System.out.println("mid : "+ mid);
            
            if(pass(mid, diffs, times, limit)) {
                // System.out.println("pass");
                end = mid-1;
                answer = mid;
            }
            else {
                // System.out.println("no");
                start = mid+1;
                // answer = mid;
            }
        }
        // System.out.println(answer);
        return answer==0?1:answer;
    }
    static boolean pass(int level, int[] diffs, int[] times, long limit) {
        long total = times[0];
        for(int i=1; i<times.length; i++) {
            int diff = diffs[i];
            int time = times[i];
            if(diff <= level) total += time;
            else total += (time+times[i-1])*(diff-level) + time;
        }
        if(total <= limit) return true;
        return false;
    }
}
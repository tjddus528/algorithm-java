class Solution {
    static int answer;
    public int solution(int n) {
        answer = 0;
        dfs(n, 0, 0);
        return answer;
    }
    static void dfs(int n, int one, int three) {
        // System.out.println(n +", cnt: " + cnt);
        if(n < 3 || n < Math.pow(3, (one-2*three)/2)) return;
        if(n==3) {
            if(one-2*three == 2) {
                answer++;
            }
        } else if (n > 3) {
            if(n%3==0 && one-2*three>=2) dfs(n/3, one, three+1);
            dfs(n-1, one+1, three);
        }
    }
}
class Solution {
    static int answer = 51;
    static String[] wordList;
    static int size;
    public int solution(String begin, String target, String[] words) {
        wordList = words;
        size = wordList.length;
        dfs(begin, target, new boolean[size], 0);
        return answer > 50 ? 0: answer;
    }
    static void dfs(String start, String target, boolean[] visited, int step) {
        if(start.equals(target)) {
            answer = Math.min(step, answer);
            return;
        }
        if(step > size) return;
        for(int i=0; i<size; i++) {
            if(visited[i]) continue;
            if(compareCount(start, wordList[i])==1) {
                visited[i] = true;
                dfs(wordList[i], target, visited, step+1);
                visited[i] = false;
            }
        }
    }
    static int compareCount(String str1, String str2) {
        int n = str1.length();
        int cnt = 0;
        for(int i=0; i<n; i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }
}
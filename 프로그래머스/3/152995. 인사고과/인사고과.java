import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int[] target = scores[0];
        int wanho = target[0]+target[1];
        Arrays.sort(scores, (a, b)-> a[0]==b[0] ? a[1]-b[1] : b[0]-a[0]);
        int maxScore = 0;
        for(int[] score: scores) {
            if(maxScore <= score[1]) {
                maxScore = score[1];
                if(score[0]+score[1]>wanho) answer++;
            }
            else {
                if(score == target) return -1;
            }
        }
        return answer;
    }
}
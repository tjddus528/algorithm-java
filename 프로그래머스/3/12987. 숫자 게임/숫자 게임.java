import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int score = 0;
        int n = A.length;
        // A정렬, B정렬
        Arrays.sort(A);
        Arrays.sort(B);
        // A 앞에서부터 이동하면서 이길 수 있는 B를 하나씩 고르기 (그럴 때마다 승점 + 1)
        int a = 0;
        int b = 0;
        while(a < n && b < n) {
            if(A[a] < B[b]) {
                a++;
                b++;
                score++;
            } else {
                b++;
            }
        }
        return score;
    }
}
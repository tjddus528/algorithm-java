import java.util.*;
class Solution {
    static int[] num;
    static int n;
    static String[] oper;
    static int[][] MAX;
    static int[][] MIN;
    public int solution(String arr[]) {
        n = (arr.length+1)/2;
        num = new int[n];
        oper = new String[n-1];
        int a = 0;
        int b = 0;
        for(String s: arr) {
            if(s.equals("-") || s.equals("+")) {
                oper[a++] = s;
            }
            else {
                num[b++] = Integer.parseInt(s);
            }
        }
        
        MAX = new int[n][n];
        MIN = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                MAX[i][j] = -20100000;
                MIN[i][j] =  20100000;
            }
        }
        for(int i=0; i<n; i++){
            MAX[i][i] = num[i];
            MIN[i][i] = num[i];
        }
        for(int len=1; len<n; len++) {
            for(int s=0; s<n-len; s++) {
                int e = s + len;
                for(int k=s; k<e; k++) {
                    if(oper[k].equals("+")) {
                        MAX[s][e] = Math.max(MAX[s][e], MAX[s][k]+MAX[k+1][e]);
                        MIN[s][e] = Math.min(MIN[s][e], MIN[s][k]+MIN[k+1][e]);
                    }
                    else {
                        MAX[s][e] = Math.max(MAX[s][e], MAX[s][k]-MIN[k+1][e]);
                        MIN[s][e] = Math.min(MIN[s][e], MIN[s][k]-MAX[k+1][e]);
                    }
                }
            }
        }
        return MAX[0][n-1];
    }
}
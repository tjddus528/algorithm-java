import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int T, N;
    static int[] arr;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            set = new HashSet<>();
            set.add(0);
            int[] score = new int[10001];
            score[0] = 1;
            for(int i=0; i<N; i++) {
                for(int j=10000; j>=0; j--) {
                    if(score[j]==1 && score[j+arr[i]]==0 && j+arr[i]<=10000) {
                        score[j+arr[i]] = 1;
                        set.add(j+arr[i]);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ").append(set.size());
            System.out.println(sb);
        }
    }
}

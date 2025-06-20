import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, K, Q, M;
    static boolean[] students;
    static int[] check;
    static int[][] range;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        K = Integer.parseInt(data[1]);
        Q = Integer.parseInt(data[2]);
        M = Integer.parseInt(data[3]);
        students = new boolean[N+3];
        Set<Integer> sleep = new HashSet<>();   // K명의 졸고 있는 학생의 입장 번호
        check = new int[Q];                     // Q명의 출석 코드를 받은 학생들의 입장 번호
        range = new int[M][2];                  // M개의 구간 범위 S, E

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) sleep.add(Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) check[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            range[i][0] = Integer.parseInt(st.nextToken());
            range[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int ch: check) {
            if(sleep.contains(ch)) continue;
            int multi = 1;
            while(ch*multi <= N+2) {
                if(!sleep.contains(ch*multi)) students[ch*multi] = true;
                multi++;
            }
        }
        for(int[] rg: range) {
            int start = rg[0];
            int end = rg[1];
            int cnt = 0;
            for(int i = start; i<=end; i++) {
                if(!students[i]) cnt++;
            }
            System.out.println(cnt);
        }

    }
}

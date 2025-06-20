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
    static int[] count;
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
        for(int i=0; i<Q; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(sleep.contains(num)) continue;
            int multi = 1;
            while(num*multi <= N+2) {
                int next = num*multi;
                if(!sleep.contains(next)) students[next] = true;
                multi++;
            }
        }

        // 누적합
        count = new int[N+3];
        for(int i=3; i<=N+2; i++) {
            count[i] = count[i-1] + (students[i]? 0 : 1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int result = count[end] - count[start-1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);

    }
}

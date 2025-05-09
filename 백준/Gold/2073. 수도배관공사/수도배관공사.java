import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int D, P;
    static int[][] pipes;
    static int maxD;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        D = Integer.parseInt(data[0]);
        P = Integer.parseInt(data[1]);
        pipes = new int[P][2];
        for (int i = 0; i < P; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pipes[i][0] = Integer.parseInt(st.nextToken());
            pipes[i][1] = Integer.parseInt(st.nextToken());
        }
        // 용량 기준 내림차순 정렬
        Arrays.sort(pipes, Comparator.comparingInt((int[] a) -> a[1]).reversed());

        // dp[i]=true면 “길이 i를 만들 수 있다”
        BitSet dp = new BitSet(D + 1);
        dp.set(0);

        // 가장 큰 용량부터 하나씩 추가하며 확인
        for (int[] pipe : pipes) {
            int len = pipe[0];
            int cap = pipe[1];

            // 수동으로 shift: i=len..D 순이 아니라 D−len..0 내려가며
            // 원래 dp[i]가 true였다면 dp[i+len]=true로 만든다
            for (int i = D - len; i >= 0; i--) {
                if (dp.get(i)) {
                    dp.set(i + len);
                }
            }

            // 이제 딱 D를 만들 수 있으면 이 cap이 최대 용량
            if (dp.get(D)) {
                System.out.println(cap);
                return;
            }
        }
    }
}

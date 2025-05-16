import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[] arr;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long[] sum = new long[N + 1];
        long[] mod = new long[M];
        long result = 0;
        // 1 <= i <= j <= N
        for (int i = 1; i <= N; i++) {
            sum[i] += sum[i - 1] + arr[i - 1];
            if(sum[i] % M == 0) result++;
            mod[(int)(sum[i] % M)]++;
        }
        for (int i = 0; i < M; i++) {
            if(mod[i] > 1) result += ((mod[i] * (mod[i] - 1))/2);
        }
        System.out.println(result);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] output;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        output = new int[M];
        dupCombi(0, 0);
        System.out.println(sb);
    }
    static void dupCombi(int depth, int start) {
        if(start >= N) return;
        if(depth == M) {
            for (int ans : output) {
                sb.append(ans).append(" ");
            }
            sb.append('\n');
            return;
        }
        output[depth] = arr[start];
        dupCombi(depth+1, start);
        dupCombi(depth, start+1);
    }
}

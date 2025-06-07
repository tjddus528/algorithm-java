import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;               // 3 <= N <= 5_000
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        long result = Long.MAX_VALUE;
        long[] output = new long[3];
        for(int k=0; k<N-2; k++) {
            int i = k+1;
            int j = N-1;
            while(i < j) {
                long sum = arr[k] + arr[i] + arr[j];
                if (Math.abs(sum) < result) {
                    result = Math.abs(sum);
                    output[0] = arr[k];
                    output[1] = arr[i];
                    output[2] = arr[j];
                    if (sum == 0)
                        break;
                }
                if (sum < 0)
                    i++;
                else
                    j--;
            }
        }
        System.out.println(output[0]+" "+output[1]+" "+output[2]);
    }
}

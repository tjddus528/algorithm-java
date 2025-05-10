import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static long[] arr;
    public static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);


        result = arr[N-1];
        long a = 0;
        long b = 0;
        if(N == 1) {
            System.out.println("1");
            return ;
        }

        for(int i=0;i<N/2;i++) {
            a = arr[i];
            if(N%2 == 0) {
                b = arr[N-1-i];
            }else if(N%2==1) {
                b = arr[N-1-1-i];
            }
            result = Math.max(result, a + b);
        }
        System.out.println(result);


    }
}

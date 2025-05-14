import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    static long total;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        total = Long.parseLong(br.readLine());

        Arrays.sort(arr);
        long result = arr[N-1];
        if (check(result)) {
            System.out.println(result);
            return;
        }

        long left = 0;
        long right = arr[N-1];
        while(left <= right) {
            long mid = (left+right)/2;
            if(check(mid)) {
                result = mid;
                left = mid + 1;
            }
            else right = mid - 1;
        }
        System.out.println(result);

    }
    static boolean check(long topPrice) {
        long sum = 0;
        for(int a: arr) {
            if(a <= topPrice) sum += a;
            else sum += topPrice;
        }
        return sum <= total;
    }
}

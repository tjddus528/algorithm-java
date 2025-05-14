import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    static int total;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        total = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int result = arr[N-1];
        if (check(result)) {
            System.out.println(result);
            return;
        }

        int left = 0;
        int right = arr[N-1];
        while(left <= right) {
            int mid = (left+right)/2;
            if(check(mid)) {
                result = mid;
                left = mid + 1;
            }
            else right = mid - 1;
        }
        System.out.println(result);

    }
    static boolean check(int topPrice) {
        int sum = 0;
        for(int a: arr) {
            if(a <= topPrice) sum += a;
            else sum += topPrice;
        }
        return sum <= total;
    }
}

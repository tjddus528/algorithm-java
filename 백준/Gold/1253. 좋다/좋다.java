import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(arr);

        int cnt = 0;
        for(int i=0; i<N; i++) {
            int left = 0;
            int right = N-1;
            while(left < right) {
                if(left == i) {
                    left++;
                    continue;
                }
                if(right == i) {
                    right--;
                    continue;
                }
                if(arr[left] + arr[right] > arr[i]) right--;
                else if (arr[left] + arr[right] < arr[i]) left++;
                else {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}

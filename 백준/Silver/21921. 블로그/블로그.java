import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int x = Integer.parseInt(data[1]);
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] maxUser = new int[n];

        int start = 0;
        int end = x;
        int sum = 0;
        int max = 0;
        for(int i=start; i<end; i++) {
            sum += arr[i];
        }

        while(end < n) {
            maxUser[start] = sum;
            max = Math.max(max, sum);
            sum -= arr[start++];
            sum += arr[end++];
        }
        maxUser[start] = sum;

        if (max > 0) {
            System.out.println(max);
            int cnt = 0;
            for(int user: maxUser)
                if(max == user) cnt++;
            System.out.println(cnt);
        } else {
            System.out.println("SAD");
        }

    }
}

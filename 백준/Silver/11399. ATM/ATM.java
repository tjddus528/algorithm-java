import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] p = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
    int[][] arr = new int[n][2];
    for(int i=0; i<n; i++) {
      arr[i] = new int[]{i, p[i]};
    }
    Arrays.sort(arr, (Comparator.comparingInt(o -> o[1])));
    int answer = 0;
    int time = 0;
    for(int[] a: arr) {
      answer += time + a[1];
      time += a[1];
    }
    System.out.println(answer);
  }

}

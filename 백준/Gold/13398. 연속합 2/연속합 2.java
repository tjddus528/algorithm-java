import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n+1];
    arr[0] = 0;

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=1; i<=n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int answer = arr[1];
    int[][] d = new int[2][n+1];
    d[0][1] = arr[1];
    d[1][1] = arr[1];
    for(int i=2; i<=n; i++) {
      d[0][i] = Math.max(arr[i], d[0][i-1]+arr[i]);
      d[1][i] = Math.max(d[1][i-1]+arr[i], d[0][i-1]);
      answer = Math.max(answer, Math.max(d[0][i], d[1][i]));
    }
    System.out.println(answer);
  }
}

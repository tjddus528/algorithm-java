import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int k = Integer.parseInt(data[1]);
    int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int s = 0;
    int e = 0;
    int result = 0;
    int answer = (-100)*n;
    while(true) {
      result += a[e++];
      if(e-s > k) result -= a[s++];
      if(e-s == k) answer = Math.max(answer, result);
      if(e >= n) break;
    }
    System.out.println(answer);
  }

}

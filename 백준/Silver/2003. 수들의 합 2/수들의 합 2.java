import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int s = 0;
    int e = 0;
    int result = 0;
    int answer = 0;
    while(s <= e && e<n) {
      result += a[e];
      while(result > m) {
        result -= a[s];
        s++;
      }
      if(result == m) answer++;
      e++;
    }
    System.out.println(answer);
  }

}

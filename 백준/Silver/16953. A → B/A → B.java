import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static long a, b;
  static int answer = 1000000000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    a = Integer.parseInt(data[0]);
    b = Integer.parseInt(data[1]);
    answer = dfs(b, 1);
    System.out.println(answer);
  }
  static int dfs(long n, int step) {
    if(n < a) return -1;
    if(n==a) return step;
    if(n%10 == 1) return dfs(n/10,step+1);
    if(n%2 == 0) return dfs(n/2, step+1);
    return -1;
  }
}
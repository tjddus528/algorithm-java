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
    dfs(a, 1);
    if (answer >= 1000000000) {
      answer = -1;
    }
    System.out.println(answer);
  }
  static void dfs(long n, int step) {
    if(n > b) return ;
    if(n==b) {
      answer = Math.min(answer, step);
      return;
    }
    dfs(n*2, step+1);
    dfs(Long.parseLong(n+"1"),step+1);
  }
}
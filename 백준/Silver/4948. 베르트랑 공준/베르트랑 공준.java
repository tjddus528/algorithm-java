import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      int x = Integer.parseInt(br.readLine());
      if (x == 0)
        break;
      boolean[] isPrime = new boolean[2*x + 1];
      for (int i = 0; i <= 2*x; i++)
        isPrime[i] = true;

      for (int i = 2; i <= 2*x; i++) {
        if (isPrime[i]) {
          int j = 2;
          while (i * j <= 2*x) {
            isPrime[i * j] = false;
            j++;
          }
        }
      }
      int cnt = 0;
      for (int i = Math.max(x+1, 2); i <= 2*x; i++) {
        if (isPrime[i]) {
          cnt++;
        }
      }
      System.out.println(cnt);
    }
  }
}

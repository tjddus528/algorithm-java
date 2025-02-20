import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int temp = n;
    int limit = 0;
    while(temp > 0) {
      temp /= 10;
      limit++;
    }
    for(int num = n - 9*limit; num < n; num++) {
      if (check(num, n)) {
        System.out.println(num);
        return;
      }
    }
    System.out.println(0);
  }
  static boolean check(int num, int n) {
    int sum = num;
    while(num > 0) {
      sum += (num % 10);
      num /= 10;
    }
    if (sum == n) return true;
    return false;
  }
}

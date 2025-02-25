import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int cnt = 0;
    for (int i = 1; i <= n; i++)
      if (check(i))
        cnt++;
    System.out.println(cnt);
  }

  static boolean check(int number) {
    int idx = 0;
    int now;
    int before = 0;
    int gap = 0;
    while(number > 0) {
      now = number % 10;
      number /= 10;
      if(idx == 1) gap = before - now;
      if (idx > 1) {
        if((before - now) != gap) return false;
      }
      before = now;
      idx++;
    }
    return true;
  }

}

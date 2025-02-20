import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for(int num = 1; num < n; num++) {
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

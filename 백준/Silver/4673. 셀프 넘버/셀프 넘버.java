import java.io.IOException;

public class Main {
  static int[] constCnt = new int[10001];
  public static void main(String[] args) throws IOException {
    // 1 ~ 10,000 까지를 각각 n이라고 하고, d(n)을 구한다.
    for(int i=1; i<=10000; i++) {
      int num = i;
      int d = num;
      while(num > 0) {
        d += num%10;
        num /= 10;
      }
      // 10,000 크기의 배열을 만들어 d(n)의 자리에 생성자의 개수를 하나씩 더한다.
      if(d<=10000) constCnt[d]++;
    }
    // 1 ~ 10,000 를 돌면서 0인 수를 출력한다.
    for(int i=1; i<= 10000; i++) {
      if(constCnt[i]==0) System.out.println(i);
    }
  }
}

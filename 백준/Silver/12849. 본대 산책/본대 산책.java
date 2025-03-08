import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static long div = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int d = Integer.parseInt(br.readLine());
    long[][] campus = new long[d+1][8];
    campus[0][0] = 1;
    for(int i=1; i<=d; i++) {
      campus[i][0] = (campus[i-1][1]+campus[i-1][2]) % div;
      campus[i][1] = (campus[i-1][0]+campus[i-1][2]+campus[i-1][3]) % div;
      campus[i][2] = (campus[i-1][0]+campus[i-1][1]+campus[i-1][3]+campus[i-1][4]) % div;
      campus[i][3] = (campus[i-1][1]+campus[i-1][2]+campus[i-1][4]+campus[i-1][5]) % div;
      campus[i][4] = (campus[i-1][2]+campus[i-1][3]+campus[i-1][5]+campus[i-1][7]) % div;
      campus[i][5] = (campus[i-1][3]+campus[i-1][4]+campus[i-1][6]) % div;
      campus[i][6] = (campus[i-1][5]+campus[i-1][7]) % div;
      campus[i][7] = (campus[i-1][4]+campus[i-1][6]) % div;
    }
    System.out.println(campus[d][0]);

  }
}

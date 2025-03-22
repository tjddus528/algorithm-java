import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int m = Integer.parseInt(br.readLine());
    int s = 0;
    while(m --> 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String oper = st.nextToken();
      int x = 0;
      if(st.hasMoreTokens()) x = Integer.parseInt(st.nextToken());
      switch (oper) {
        case "add":
          s |= (1<<x);
          break;
        case "remove":
          s &= ~(1<<x);
          break;
        case "check":
          int checked = (s & (1<<x)) > 1?1:0;
          sb.append(checked).append("\n");
          break;
        case "toggle":
          s ^= (1<<x);
          break;
        case "all":
          s = (1<<21) - 1;
          break;
        case "empty":
          s = 0;
          break;
        default:
          break;
      }
    }
    System.out.println(sb.toString());
  }
}
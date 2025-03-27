import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
  static Map<Long,Long> map = new HashMap<>();
  static long n, p, q;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Long.parseLong(data[0]);
    p = Long.parseLong(data[1]);
    q = Long.parseLong(data[2]);

    map.put(0L, 1L);
    System.out.println(dp(n));
  }
  static long dp(long n) {
    if(map.containsKey(n)) return map.get(n);
    map.put(n, dp(n/p)+dp(n/q));
    return map.get(n);
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int k = Integer.parseInt(data[1]);
    List<Integer> num = new ArrayList<>();
    for(int i=1; i<=n; i++) {
      if(n%i==0) num.add(i);
    }
    if (num.size() < k) {
      System.out.println(0);
    } else{
      System.out.println(num.get(k-1));
    }
  }
}

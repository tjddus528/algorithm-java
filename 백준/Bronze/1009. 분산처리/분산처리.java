import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    while(n-->0){
      String[] data = br.readLine().split(" ");
      int a = Integer.parseInt(data[0]);
      int b = Integer.parseInt(data[1]);
      long number = 1;
      for(int i=0; i<b; i++) {
        number *= a;
        number %= 10;
      }
      if(number==0) number+=10;
      System.out.println(number);
    }
  }
}

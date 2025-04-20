import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while(true) {
      String str = br.readLine();
      if(str == null) break;

      String[] data = str.split(" ");
      System.out.println(check(data[0], data[1]));
    }
  }
  static String check(String s, String t) {
    int sIdx = 0;
    for(int i=0; i<t.length(); i++) {
      if(t.charAt(i) == s.charAt(sIdx)) {
        sIdx++;
      }
      if(sIdx >= s.length()) return "Yes";
    }
    return "No";
  }
}

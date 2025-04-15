import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split("-");
    List<Integer> sum = new ArrayList<>();
    for(String d: data) {
      int total = 0;
      for(String n: d.split("\\+")) {
        total += Integer.parseInt(n);
      }
      sum.add(total);
    }
    int result = sum.get(0);
    for(int i=1; i< sum.size(); i++) {
      result -= sum.get(i);
    }
    System.out.println(result);
  }
}

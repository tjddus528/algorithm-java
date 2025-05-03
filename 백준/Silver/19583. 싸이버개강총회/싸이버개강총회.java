import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
  static String S, E, Q;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    S = data[0];
    E = data[1];
    Q = data[2];

    Set<String> before = new HashSet<>();
    Set<String> after = new HashSet<>();
    int cnt = 0;
    while(true){
      String input = br.readLine();
      if (input == null || input.equals(""))
        break;
      String[] member = input.split(" ");
      String time = member[0];
      String name = member[1];

      if(time.compareTo(S) <= 0) before.add(name);
      else if(time.compareTo(E) >= 0 && time.compareTo(Q) <= 0) after.add(name);
    }
    for(String name: before) {
      if(after.contains(name)) cnt++;
    }
    System.out.println(cnt);
  }
}

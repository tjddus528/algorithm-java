import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    List<String> result = new ArrayList<>();
    Set<String> set = new HashSet<>();
    for(int i=0; i<n; i++) {
      String name = br.readLine();
      set.add(name);
    }
    for(int i=0; i<m; i++) {
      String name = br.readLine();
      if(set.contains(name)) {
        result.add(name);
      }
    }
    Collections.sort(result);
    System.out.println(result.size());
    for (String r : result) {
      System.out.println(r);
    }
  }
}

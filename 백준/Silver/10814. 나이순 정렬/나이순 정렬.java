import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    List<String[]> user = new ArrayList<>();
    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String age = st.nextToken();
      String name = st.nextToken();
      user.add(new String[]{String.valueOf(i), age, name});
    }
    user.sort((o1, o2) -> {
      if(Objects.equals(o1[1], o1[2])) {
        return Integer.parseInt(o1[0])-Integer.parseInt(o2[0]);
      } else {
        return Integer.parseInt(o1[1])-Integer.parseInt(o2[1]);
      }
    });
    for(int i=0; i<n; i++) {
      System.out.println(user.get(i)[1] + " " + user.get(i)[2]);
    }

  }
}

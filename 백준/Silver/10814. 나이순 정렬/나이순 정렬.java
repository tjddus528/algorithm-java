import java.io.*;
import java.util.*;

public class Main {
  static StringBuilder sb = new StringBuilder();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Map<Integer, String> nameDict = new HashMap<>();
    List<int[]> user = new ArrayList<>();
    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int age = Integer.parseInt(st.nextToken());
      String name = st.nextToken();
      user.add(new int[]{i, age});
      nameDict.put(i, name);
    }
    user.sort((Comparator.comparingInt(o -> o[1])));
    for(int i=0; i<n; i++) {
      sb.append(user.get(i)[1]).append(" " + nameDict.get(user.get(i)[0])+"\n");
    }
    System.out.println(sb);
    br.close();
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    Set<Integer> setA = new HashSet<>();
    Set<Integer> setB = new HashSet<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      setA.add(Integer.parseInt(st.nextToken()));
    }
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<m; i++) {
      setB.add(Integer.parseInt(st.nextToken()));
    }
    int resultA = 0;
    for(int a: setA) {
      if(setB.contains(a)) {
        resultA++;
      }
    }
    int resultB = 0;
    for(int b: setB) {
      if(setA.contains(b)) {
        resultB++;
      }
    }
    System.out.println(n+m-resultA-resultB);
  }
}

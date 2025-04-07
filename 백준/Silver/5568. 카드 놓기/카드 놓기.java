import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
  static int n, k;
  static int[] card;
  static boolean[] visited;
  static String[] output;
  static Set<String> set = new HashSet<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    k = Integer.parseInt(br.readLine());
    card = new int[n];
    for (int i = 0; i < n; i++) {
      card[i] = Integer.parseInt(br.readLine());
    }
    visited = new boolean[n];
    output = new String[k];
    permutation(0);
    System.out.println(set.size());
  }
  static void permutation(int depth) {
    if(depth == k) {
      String result = String.join("", output);
      set.add(result);
      return;
    }
    for(int i=0; i<n; i++) {
      if(visited[i]) continue;
      visited[i] = true;
      output[depth] = String.valueOf(card[i]);
      permutation(depth+ 1);
      visited[i] = false;
    }
  }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[] cards;
  static int answer = 0;
  static boolean[] visited;
  static int n, m;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    cards = new int[n];
    visited = new boolean[n];
    for(int i=0; i<n; i++) {
      cards[i] = Integer.parseInt(st.nextToken());
    }
    combination(0, n, 3, 0, 0);
    System.out.println(answer);

  }

  static void combination(int depth, int n, int r, int sum, int count) {
    if (count == r) {
      if (sum <= m) answer = Math.max(sum, answer);
      return;
    }
    if (depth == n) return;

    visited[depth] = true;
    combination(depth+1, n, r, sum+cards[depth], count+1);
    visited[depth] = false;
    combination(depth+1, n, r, sum, count);

  }
}

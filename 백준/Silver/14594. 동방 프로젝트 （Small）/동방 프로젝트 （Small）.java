import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] room;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    room = new int[N+1];
    for(int i=1; i<=N; i++) {
      room[i] = i;
    }
    for(int i=0; i<M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int start = room[x];
      int last = room[y];
      while(x <= N) {
        if(x<=y) room[x] = start;
        else if (room[x] == last) room[x] = start;
        x++;
      }
    }
    Set<Integer> result = new HashSet<>();
    for(int i=1; i<=N; i++)
      result.add(room[i]);
    System.out.println(result.size());

  }
  public static void union(int x, int y) {
    x = findParent(x);
    y = findParent(y);
    if (x < y) room[y] = x;
    else       room[x] = y;
  }
  public static int findParent(int i) {
    if (room[i] != i) {
      return findParent(room[i]);
    }
    return i;
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int n;
  static boolean[][] possible;
  static boolean cycle;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    possible = new boolean[n+1][n+1];
    // 인접 노드 방문 가능 여부 true 초기화
    for(int i=1; i<n; i++) {
      int m = Integer.parseInt(br.readLine());
      String[] data = br.readLine().split(" ");
      for(int j=0; j<m; j++) {
        possible[i][Integer.parseInt(data[j])] = true;
      }
    }
    // 플로이드 워셜 -> 방문 가능 여부 true
    for(int k=1; k<=n; k++) {
      for(int i=1; i<=n; i++) {
        for(int j=1; j<=n; j++) {
          possible[i][j] = (possible[i][k] && possible[k][j]) || possible[i][j];
        }
      }
    }
    for(int k=1; k<n; k++){
      if(possible[1][k] && possible[k][k]) {
        cycle = true;
        break;
      }
    }
    if (cycle) {
      System.out.println("CYCLE");
    } else {
      System.out.println("NO CYCLE");
    }
  }
}
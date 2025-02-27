import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[][] matrix;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int n = Integer.parseInt(br.readLine());
    matrix = new int[n][n];
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 플로이드 워셜
    // k번 노드를 거쳐갈 때
    for(int k=0; k<n; k++) {
      // i번 노드 -> j번 노드로 가는 경로
      for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
          if(matrix[i][k]==1 && matrix[k][j]==1) matrix[i][j] = 1;
        }
      }
    }
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        System.out.print(matrix[i][j]+" ");
      }
      System.out.println();
    }
  }
}

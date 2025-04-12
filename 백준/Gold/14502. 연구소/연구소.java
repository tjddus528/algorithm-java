import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static boolean[][] visited;
  static boolean[][] spread;
  static int[][] matrix;
  static List<int[]> virus;
  static List<int[]> wall;
  static int n, m;
  public static void main(String[] args) throws IOException {
    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Integer.parseInt(data[0]);
    m = Integer.parseInt(data[1]);
    matrix = new int[n][m];
    virus = new ArrayList<>();
    wall = new ArrayList<>();
    visited = new boolean[n][m];
    for(int i=0; i<n; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<m; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
        if(matrix[i][j]==2) {
          virus.add(new int[]{i, j});
        }
        if(matrix[i][j]==0) {
          wall.add(new int[]{i, j});
        }
      }
    }

    // 안정영역 크기 변수 초기화
    int maxArea = 0;

    // 벽 3개 세우기
    for(int i=0;i<wall.size(); i++){
      for(int j=i+1; j<wall.size();j++){
        for(int k=j+1; k<wall.size(); k++) {
          matrix[wall.get(i)[0]][wall.get(i)[1]] = 1;
          matrix[wall.get(j)[0]][wall.get(j)[1]] = 1;
          matrix[wall.get(k)[0]][wall.get(k)[1]] = 1;

          // 바이러스 퍼뜨리기
          spread = new boolean[n][m];
          for(int[] v: virus) {
            if(spread[v[0]][v[1]]) continue;
            virus_dfs(v[0], v[1]);
          }
          // 안정영역 계산하기
          visited = new boolean[n][m];
          int sum = 0;
          for (int ii = 0; ii < n; ii++) {
            for (int jj = 0; jj < m; jj++) {
              if(visited[ii][jj]) continue;
              if(spread[ii][jj]) continue;
              if (matrix[ii][jj] == 0) {
                sum += safe_dfs(ii, jj);
              }
            }
          }
          maxArea = Math.max(maxArea, sum);

          matrix[wall.get(i)[0]][wall.get(i)[1]] = 0;
          matrix[wall.get(j)[0]][wall.get(j)[1]] = 0;
          matrix[wall.get(k)[0]][wall.get(k)[1]] = 0;
        }
      }
    }
    System.out.println(maxArea);
  }
  static void virus_dfs(int x, int y) {
    spread[x][y] = true;
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      if(nx<0||ny<0||nx>n-1||ny>m-1) continue;
      if(spread[nx][ny]) continue;
      if(matrix[nx][ny]==1) continue;
      virus_dfs(nx, ny);
    }
  }
  static int safe_dfs(int x, int y) {
    int area = 1;
    visited[x][y] = true;
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      if(nx<0||ny<0||nx>n-1||ny>m-1) continue;
      if(visited[nx][ny]) continue;
      if(spread[nx][ny]) continue;
      if(matrix[nx][ny]!=0) continue;
      area += safe_dfs(nx, ny);
    }
    return area;
  }
}

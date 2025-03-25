import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  // 북:0, 동:1, 남:2, 서:3
  // 반시계 : 북(0) -> 서(3) -> 남(2) -> 동(1)
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int n, m;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Integer.parseInt(data[0]);
    m = Integer.parseInt(data[1]);
    int[] robot = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[][] map = new int[n][m];
    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(robot);
    while(!q.isEmpty()) {
      int[] now = q.poll();
      int d = now[2];
      if(map[now[0]][now[1]] == 0) {
        map[now[0]][now[1]] = 2;
        answer++;
      }

      // 주변 4칸 중 청소되지 않은 빈 칸이 없다
      if(!existEmpty(map, now[0], now[1])) {
        // 방향 d를 유지한 채, 뒤로 한 칸 후진
        int[] next = new int[]{now[0]-dx[d], now[1]-dy[d], d};

        // 후진이 불가능할 경우
        if(next[0] < 0 || next[1] < 0|| next[0] >n-1 ||next[1] >m-1 || map[next[0]][next[1]]==1) {
          break;
        }
        // 후진이 가능한 경우
        q.offer(next);
        // 그곳이 청소되지 않은 곳이라면
        if(map[next[0]][next[1]]==0) {
          map[next[0]][next[1]] = 2;
          answer++;
        }
      }
      // 주변 4칸 중 청소되지 않은 빈 칸이 있다
      else {
//        if(now[0] == 9 && now[1] == 3) break;
        // 반시계 90도 회전 후, 앞쪽 칸
        d = (d-1) < 0 ? 3: d-1;
        int[] next = new int[]{now[0]+dx[d], now[1]+dy[d], d};

        // 이동이 불가능하다면 패스
        if(next[0] < 0 || next[1] < 0|| next[0] >n-1 ||next[1] >m-1) continue;
        // 이동이 가능하다면
        // 그곳이 청소되지 않은 곳이라면
        if(map[next[0]][next[1]] == 0) {
          map[next[0]][next[1]] = 2;
          answer++;
          q.offer(next);
        } else {
          q.offer(new int[]{now[0], now[1], d});
        }
      }
    }
    System.out.println(answer);

  }
  static boolean existEmpty(int[][] map, int x, int y) {
    if(x-1>=0   && map[x-1][y] == 0) return true;
    if(x+1<=n-1 && map[x+1][y] == 0) return true;
    if(y-1>=0   && map[x][y-1] == 0) return true;
    if(y+1<=m-1 && map[x][y+1] == 0) return true;
    return false;
  }
}

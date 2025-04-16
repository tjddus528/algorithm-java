import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = {-1, 1, 0, 0, 0, 0};
  static int[] dy = {0, 0, -1, 1, 0, 0};
  static int[] dh = {0, 0, 0, 0, -1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int m = Integer.parseInt(data[0]);
    int n = Integer.parseInt(data[1]);
    int h = Integer.parseInt(data[2]);
    int[][][] box = new int[h][n][m];
    List<Node> tomato = new ArrayList<>();
    for(int i=0; i<h; i++) {
      for(int j=0; j<n; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int k=0; k<m; k++) {
          box[i][j][k] = Integer.parseInt(st.nextToken());
          if(box[i][j][k]==1) {
            tomato.add(new Node(j,k,i));
          }
        }
      }
    }

    if(all(box, n, m, h)) {
      System.out.println(0);
      return;
    }

    ArrayDeque<Node> q = new ArrayDeque<>();
    boolean[][][] visited = new boolean[h][n][m];
    for(Node t: tomato) {
      if(!visited[t.h][t.x][t.y]) {
        q.offer(new Node(t.x, t.y, t.h, 0));
        visited[t.h][t.x][t.y] = true;
      }
    }
    int min_day = n*m*h+1;
    while(!q.isEmpty()) {
      Node cur_tomato = q.poll();
      min_day = cur_tomato.day;
      for(int i=0; i<6; i++) {
        int nx = cur_tomato.x + dx[i];
        int ny = cur_tomato.y + dy[i];
        int nh = cur_tomato.h + dh[i];
        int day = cur_tomato.day + 1;
        if(nx<0 || ny<0 || nh <0 || nx>n-1 || ny>m-1 || nh > h-1) continue;
        if(visited[nh][nx][ny]) continue;
        if(box[nh][nx][ny] == 0) {
          box[nh][nx][ny] = 1;
          visited[nh][nx][ny] = true;
          q.offer(new Node(nx, ny, nh, day));
        }
      }
    }
    if(all(box, n, m, h)) {
      System.out.println(min_day);
    } else {
      System.out.println(-1);
    }
  }
  static boolean all(int[][][] box, int n, int m, int h) {
    for(int i=0; i<h; i++) {
      for(int j=0; j<n; j++) {
        for(int k=0; k<m; k++) {
          if(box[i][j][k]==0) return false;
        }
      }
    }
    return true;
  }
  static class Node {
    int x;
    int y;
    int h;
    int day = 0;
    Node(int x, int y, int h, int day) {
      this.x = x;
      this.y = y;
      this.h = h;
      this.day = day;
    }
    Node(int x, int y, int h) {
      this.x = x;
      this.y = y;
      this.h = h;
    }
    @Override
    public String toString() {
      return this.x + ", " + this.y + ", "+ this.h;
    }
  }
}

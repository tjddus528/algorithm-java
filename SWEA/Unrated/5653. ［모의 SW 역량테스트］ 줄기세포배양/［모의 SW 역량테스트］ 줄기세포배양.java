import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, K;
    static int[][] arr = new int[1000][1000];
    static class Cell implements Comparable<Cell>{
        int x, y, X;
        int activeTime;
        boolean dead;
        public Cell(int x, int y, int _X, int activeTime, boolean dead) {
            this.x = x;
            this.y = y;
            this.X = _X;
            this.activeTime = activeTime;
            this.dead = dead;
        }
        @Override
        public int compareTo(Cell o) {
            // 활성화되는 시간(activeTime)이 빠른 순, 같은 시간에 활성화되면 생명력 수치(X)가 높은 순
            return this.activeTime != o.activeTime ? Integer.compare(this.activeTime, o.activeTime) : Integer.compare(o.X, this.X);
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            for(int i=0; i<1000; i++) Arrays.fill(arr[i], 0);

            PriorityQueue<Cell> cells = new PriorityQueue<>();
            for(int i=500; i<500+N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=500; j<500+M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] > 0)
                        cells.add(new Cell(i, j, arr[i][j], arr[i][j], false));
                }
            }
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            for(int curTime = 0; curTime < K; curTime++) {
                ArrayDeque<Cell> next = new ArrayDeque<>();
                while(!cells.isEmpty()) {
                    Cell cell = cells.poll();
                    int x = cell.x;
                    int y = cell.y;
                    // 비활성화 상태인 세포
                    if(curTime < cell.activeTime) {
                        next.add(cell);
                    }
                    // 활성화 상태인 세포, 첫 1시간
                    else if (curTime == cell.activeTime) {
                        // System.out.println(x + ", " + y + " ACTIVE");
                        for(int d=0; d<4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            if(arr[nx][ny] == 0) {
                                arr[nx][ny] = cell.X;
                                next.add(new Cell(nx, ny, cell.X, curTime+cell.X+1, false));
                            }
                        }
                        next.add(cell);
                    }
                    else {
                        next.add(cell);
                    }
                    
                }

                while(!next.isEmpty()) {
                    Cell cell = next.poll();
                    // 죽은 세포
                    if (curTime >= cell.activeTime + cell.X) continue;
                    cells.add(cell);
                }
            }
            ArrayDeque<Cell> alive = new ArrayDeque<>();
            while (!cells.isEmpty()) {
                Cell c = cells.poll();
                if (K >= c.activeTime + c.X) continue; // K 시점에 죽은 애는 제외
                alive.add(c);
            }
            int size = alive.size();
            sb.append("#").append(t).append(" ").append(size).append("\n");
        }
        System.out.println(sb.toString());
    }

}

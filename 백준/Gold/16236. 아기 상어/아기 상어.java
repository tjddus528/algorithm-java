import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static class Shark {
        int size;
        int x, y;
        int eatCnt;
        int time;
        Shark(int x, int y) {
            this.size = 2;
            this.x = x;
            this.y = y;
            this.eatCnt = 0;
        }
        public void eat(int x, int y) {
            eatCnt++;
            if(eatCnt == size) {
                size++;
                eatCnt = 0;
            }
        }
        public void move(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time += time;
        }
    }
    static int N;
    static int[][] matrix;
    static Shark shark;
    static int fishCnt;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] fish = new int[8];
    static PriorityQueue<int[]> canEatFish;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 9) shark = new Shark(i, j);
                else if (matrix[i][j] > 0) {
                    fish[matrix[i][j]]++;
                    fishCnt++;
                }
            }
        }
        // BFS : 아기상어 위치로부터 주변에 물고기를 찾으러 이동, 가장 먼저 찾을 경우 해당 위치로 이동, 이동시간 계산
        // 자신의 크기가 같은 수의 물고기를 먹을 때마다 아기 상어의 크기를 1 증가 시키기
        // 위 과정을 먹을 수 있는 물고기가 없을 때까지 반복 (자신보다 작은 물고기의 개수가 있을 때까지)
        canEatFish = new PriorityQueue<>((o1, o2) -> {
            if(o1[2]==o2[2]) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
            return o1[2]-o2[2];
        });
        while(checkFish()) {
            canEatFish.clear();
            bfs(shark.x, shark.y);

            if(canEatFish.isEmpty()) break;
            int[] next = canEatFish.poll();
            matrix[shark.x][shark.y] = 0;
            shark.move(next[0], next[1], next[2]);
            shark.eat(next[0], next[1]);
            fish[matrix[next[0]][next[1]]]--;
            matrix[next[0]][next[1]] = 0;
        }
        System.out.println(shark.time);
    }
    static boolean checkFish() {
        for(int i=1; i<Math.min(shark.size, 8); i++) {
            if(fish[i] > 0) return true;
        }
        return false;
    }

    static void bfs(int sx, int sy) {
        boolean[][] visited = new boolean[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[sx][sy] = true;

        q.add(new int[]{sx, sy, 0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
                if(visited[nx][ny]) continue;
                if(matrix[nx][ny] > shark.size) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, cur[2]+1});
                if(matrix[nx][ny]>0 && matrix[nx][ny] < shark.size)
                    canEatFish.add(new int[]{nx, ny, cur[2]+1});
            }
        }

    }
    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
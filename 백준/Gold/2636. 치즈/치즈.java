import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static List<int[]> cheese;
    static List<int[]> hole;
    static int[][] tempForHole;
    static boolean isHole;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        arr = new int[N][M];

        cheese = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1) {
                    cheese.add(new int[]{i, j});
                }
            }
        }
        int hours = 0;
        while(!cheese.isEmpty()) {
            hours++;

            // 구멍의 위치 찾기
            findHole();

            // 치즈 녹이고, 다음 치즈의 위치 저장
            List<int[]> nextCheese = new ArrayList<>();
            cheeseMelt(nextCheese);
            
            if (nextCheese.isEmpty())
                break;
            
            // 기존 리스트의 주소를 새로운 치즈 리스트의 주소로 변경해주기
            cheese = nextCheese;
        }

        System.out.println(hours);
        System.out.println(cheese.size());
    }

    private static void cheeseMelt(List<int[]> nextCheese) {
        for (int[] ch : cheese) {
            // 상하좌우 공기와 맞닿아 있는 경우 해당 좌표를 2로 변경
            if (melt(ch[0], ch[1]))
                arr[ch[0]][ch[1]] = 2;

            // 녹지 않은 치즈는 새로운 리스트로 추가하기
            else
                nextCheese.add(ch);
        }
        // 2로 변경한 치즈좌표를 0으로 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 2)
                    arr[i][j] = 0;
            }
        }
    }

    private static void findHole() {
        hole = new ArrayList<>();
        List<int[]> candi = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isAir(i, j)) continue;
                if(visited[i][j]) continue;
                isHole = true;
                if(arr[i][j] == 0) {
                    bfs(i, j, visited, candi);
                }
                if(!isHole) candi = new ArrayList<>();
                hole.addAll(candi);
            }
        }
        // 공기가 아닌 치즈의 구멍인 곳의 위치 표시
        tempForHole = new int[N][M];
        for(int[] h: hole) {
            tempForHole[h[0]][h[1]] = -1;
        }
    }

    private static boolean melt(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 공기인 곳 && 치즈의 구멍이 아닌 곳
            if(arr[nx][ny]==0 && tempForHole[nx][ny] != -1) return true;
        }
        return false;
    }
    private static void bfs(int x, int y, boolean[][] visited, List<int[]> candi) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            candi.add(now);
            if(isAir(now[0], now[1])) {
                isHole = false;
            }
            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;
                if(visited[nx][ny]) continue;
                if(arr[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
    private static boolean isAir(int x, int y) {
        if(x==0 || y==0 || x==N-1 || y ==M-1) return true;
        return false;
    }

}

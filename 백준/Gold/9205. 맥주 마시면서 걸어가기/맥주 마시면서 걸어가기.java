import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기
public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static String result;
    static class BeerWalk implements Comparable<BeerWalk> {
        int x;
        int y;
        int cnt;

        public BeerWalk(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        // 맥주가 많이 남은 순으로 정렬(내림차순)
        @Override
        public int compareTo(BeerWalk o) {
            return Integer.compare(o.cnt, this.cnt);
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N+2][N+2];
            for(int i=0; i<N+2; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            result = "sad";
            visited = new boolean[N+2];
            solve(0, arr[0][0], arr[0][1], 20);
            System.out.println(result);
            
        }
    }
    static void solve(int depth, int x, int y, int beer) {
        
        if(x == arr[N+1][0] && y == arr[N+1][1]) {
            result = "happy";
            return;
        }
        // 편의점이면 beer RESET
        beer = 20;
       
        visited[depth] = true;
        for(int i=0; i<N+2; i++) {
            int nx = arr[i][0];
            int ny = arr[i][1];

            // 같은 좌표로는 이동하지 못함
            if(nx == x && ny == y) continue;
            // 이동했던 좌표로는 이동하지 못함
            if(visited[i]) continue;
            // 맨해튼 거리를 구해서, 현재 맥주로 가지 못하면
            int distance = dist(x, y, nx, ny);
            if(distance > 50 * beer) continue;
            // 이동 가능하면 맥주를 소비하고 q에 넣기
            int afterBeer = beer - distance / 50 ;
            solve(i, nx, ny, afterBeer);
        }

    }

    static int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1- x2) + Math.abs(y1 - y2);
    }

}

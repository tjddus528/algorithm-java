import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int minPrice = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] flower = new boolean[N][N];
        dfs(0, 0, flower);
        System.out.println(minPrice);

    }
    static void dfs(int depth, int price, boolean[][] flower) {
        if(depth == 3) {
            minPrice = Math.min(minPrice, price);
            return;
        }
        for(int i=1; i<N; i++) {
            for(int j=1; j<N; j++) {
                if(flower[i][j]) continue;
                if(!possible(i, j, flower)) continue;
                seed(i, j, flower);
                dfs(depth+1, price+calPrice(i, j), flower);
                unseed(i, j, flower);
            }
        }
    }
    static boolean possible(int x, int y, boolean[][] flower) {
        if(x-1<0 || y-1<0 || x+1 >N-1 || y+1 >N-1) return false;
        if(flower[x-1][y] || flower[x][y-1] || flower[x+1][y] || flower[x][y+1]) return false;
        return true;
    }
    static void seed(int x, int y, boolean[][] flower) {
        flower[x][y] = true;
        if(x-1>=0) flower[x-1][y] = true;
        if(y-1>=0) flower[x][y-1] = true;
        if(x+1<N)  flower[x+1][y] = true;
        if(y+1<N)  flower[x][y+1] = true;
    }
    static void unseed(int x, int y, boolean[][] flower) {
        flower[x][y] = false;
        if(x-1>=0) flower[x-1][y] = false;
        if(y-1>=0) flower[x][y-1] = false;
        if(x+1<N)  flower[x+1][y] = false;
        if(y+1<N)  flower[x][y+1] = false;
    }
    static int calPrice(int x, int y) {
        int total = 0;
        total += arr[x][y];
        if(x-1>=0) total += arr[x-1][y];
        if(y-1>=0) total += arr[x][y-1];
        if(x+1<N)  total += arr[x+1][y];
        if(y+1<N)  total += arr[x][y+1];
        return total;
    }
}

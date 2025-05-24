import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    static int T;
    static int N = 4;
    static int[][] arr;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int test = 1; test <=T; test++) {
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            set.clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dfs(i, j, 0, "" + arr[i][j]);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test).append(" ").append(set.size());
            System.out.println(sb);
        }
    }
    static void dfs(int x, int y, int depth, String str) {
        if(str.length() >= 7) {
            set.add(str);
            return;
        }
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
            dfs(nx, ny, depth+1, str+arr[nx][ny]);
        }
    }
}

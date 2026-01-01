import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] city;
    static List<int[]> chicken = new ArrayList<>();
    static List<int[]> house = new ArrayList<>();
    static int[] checked;
    static int totalDist = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 1) house.add(new int[]{i, j});
                if(city[i][j] == 2) chicken.add(new int[]{i, j});
            }
        }
        checked = new int[chicken.size()];
        combi(0, 0);
        System.out.println(totalDist);


    }
    static void combi(int n, int cnt) {
        if(cnt == M) {
            totalDist = Math.min(totalDist, calDist(checked));
            return;
        }
        if(n >= chicken.size()) return;

        checked[n] = 1;
        combi(n+1, cnt+1);

        checked[n] = 0;
        combi(n+1, cnt);

    }

    static int calDist(int[] checked) {
        int total = 0;
        for(int[] h: house) {
            int dist = Integer.MAX_VALUE;
            for(int i=0; i<checked.length; i++) {
                if(checked[i]==1) {
                    dist = Math.min(dist, Math.abs(chicken.get(i)[0] - h[0]) + Math.abs(chicken.get(i)[1] - h[1]));
                }
            }
            total += dist;
        }
        return total;
    }

}

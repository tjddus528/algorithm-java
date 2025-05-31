import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] eggs;
    static int maxCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        for(int i=0; i<N; i++) {
            // 내구도, 무게
            String[] data = br.readLine().split(" ");
            eggs[i][0] = Integer.parseInt(data[0]);
            eggs[i][1] = Integer.parseInt(data[1]);
        }

        dfs(0, 0);
        System.out.println(maxCnt);

    }
    static void dfs(int depth, int cnt) {
        if(depth == N) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        if(eggs[depth][0] <=0 || cnt == N-1) {
            dfs(depth+1, cnt);
            return;
        }

        int originCnt = cnt;
        for(int i=0; i<N; i++) {
            if(depth == i) continue;
            if(eggs[i][0] <= 0) continue;

            int[] pick = eggs[i];
            eggs[depth][0] -= pick[1];
            pick[0] -= eggs[depth][1];

            // 손에 든 계란이 깨짐
            if(eggs[depth][0] <= 0) {
                cnt++;
            }
            // 손에 든 계란이 아닌 계란 중 선택했던 계란이 깨짐
            if(pick[0] <= 0) {
                cnt++;
            }

            dfs(depth+1, cnt);
            eggs[depth][0] += pick[1];
            pick[0] += eggs[depth][1];
            cnt = originCnt;
        }
    }

}

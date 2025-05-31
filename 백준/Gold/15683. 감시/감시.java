import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    // 상, 하, 좌, 우
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static Map<Integer, List<int[]>> cctvDir = new HashMap<>();
    static List<int[]> cctvLocation = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        // 값을 저장하면서 CCTV 번호도 함께 저장
        arr = new int[N][M];
        cctvLocation = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] >=1 && arr[i][j] <= 5) {
                    cctvLocation.add(new int[]{arr[i][j], i, j});
                }
            }
        }
        initCCTV();
        dfs(0, arr);
        System.out.println(result);

    }
    static void dfs(int depth, int[][] curMap) {
        if(depth == cctvLocation.size()) {
            int cnt = getZeroCnt(curMap);
            result = Math.min(cnt, result);
            return;
        }

        int[] cctvInfo = cctvLocation.get(depth);
        int num = cctvInfo[0];
        int x = cctvInfo[1];
        int y = cctvInfo[2];

        for(int[] dirs: cctvDir.get(num)) {
            int[][] temp = copyMap(curMap);
            for(int dir: dirs) {
                watch(x, y, temp, dir);
            }
            dfs(depth+1, temp);
        }
    }
    private static void watch(int x, int y, int[][] temp, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
            if (temp[nx][ny] == 6) break;
            if (temp[nx][ny] == 0) temp[nx][ny] = -1;
            nx += dx[dir];
            ny += dy[dir];
        }
    }
    private static int[][] copyMap(int[][] original) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, M);
        }
        return copy;
    }
    static int getZeroCnt(int[][] temp) {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(temp[i][j]==0) cnt++;
            }
        }
        return cnt;
    }
    // CCTV 번호에 따라서 방향의 경우의 수가 다름
    static void initCCTV() {
        // 1번 CCTV: 4가지 (상, 하, 좌, 우)
        cctvDir.put(1, Arrays.asList(
                new int[]{0},
                new int[]{1},
                new int[]{2},
                new int[]{3}
        ));

        // 2번 CCTV: 2가지 (상+하, 좌+우)
        cctvDir.put(2, Arrays.asList(
                new int[]{0, 1},
                new int[]{2, 3}
        ));

        // 3번 CCTV: 4가지 (상+우, 우+하, 하+좌, 좌+상)
        cctvDir.put(3, Arrays.asList(
                new int[]{0, 3},
                new int[]{3, 1},
                new int[]{1, 2},
                new int[]{2, 0}
        ));

        // 4번 CCTV: 4가지 (상+좌+우, 상+하+우, 하+좌+우, 상+하+좌)
        cctvDir.put(4, Arrays.asList(
                new int[]{0, 2, 3},
                new int[]{0, 1, 3},
                new int[]{1, 2, 3},
                new int[]{0, 1, 2}
        ));

        // 5번 CCTV: 1가지 (상+하+좌+우)
        cctvDir.put(5, Collections.singletonList(
                new int[]{0, 1, 2, 3}
        ));
    }

    static void checkCCTV(boolean[] direction, int x, int y, int[][] temp) {
        for(int i=0; i<4; i++) {
            if(!direction[i]) continue;
            switch (i) {
                case 0: checkUp(x, y, temp); break;
                case 1: checkDown(x, y, temp); break;
                case 2: checkLeft(x, y, temp); break;
                case 3: checkRight(x, y, temp); break;
            }
        }
    }
    static void checkUp(int x, int y, int[][] temp) {
        for(int i=x-1; i>=0; i--) {
            if(temp[i][y] == 6) return;
            if(temp[i][y] != 0) continue;
            temp[x][i] = -1;
        }
    }
    static void checkDown(int x, int y, int[][] temp) {
        for(int i=x+1; i<N; i++) {
            if(temp[i][y] == 6) return;
            if(temp[i][y] != 0) continue;
            temp[x][i] = -1;
        }
    }
    static void checkLeft(int x, int y, int[][] temp) {
        for(int i=y-1; i>=0; i--) {
            if(temp[x][i] == 6) return;
            if(temp[x][i] != 0) continue;
            temp[x][i] = -1;
        }
    }
    static void checkRight(int x, int y, int[][] temp) {
        for(int i=y+1; i<M; i++) {
            if(temp[x][i] == 6) return;
            if(temp[x][i] != 0) continue;
            temp[x][i] = -1;
        }
    }

}

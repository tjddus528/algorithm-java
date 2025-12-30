import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static class Position {
        int power;
        boolean robot;
        Position(int p) {this.power = p;}
    }
    static Position[] belts;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belts = new Position[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) {
            belts[i] = new Position(Integer.parseInt(st.nextToken()));
        }

        int step = 0;
        int totalZeroCnt = 0;
        while(true) {
            step++;
            // 벨트 한 칸 회전
            int tempPower = belts[2 * N - 1].power;
            boolean tempRobot = belts[2 * N - 1].robot;
            for (int i = 2 * N - 2; i >= 0; i--) {
                belts[i + 1].power = belts[i].power;
                belts[i + 1].robot = belts[i].robot;
            }
            belts[0].power = tempPower;
            belts[0].robot = tempRobot;
            belts[N-1].robot = false;

            // 로봇이 벨트 위에서 한 칸 이동 (이때 로봇이 이동하는 칸의 내구도 -1)
            for (int i = N - 1; i >= 0; i--) {
                // 로봇이 없으면 pass
                if (!belts[i].robot)
                    continue;
                // 내리는 위치라면 즉시 내린다.
                if (i == N - 1) {
                    belts[i].robot = false;
                    continue;
                }
                if (!belts[i+1].robot && belts[i + 1].power > 0) {
                    belts[i + 1].robot = true;
                    belts[i].robot = false;
                    belts[i + 1].power--;
                    if (belts[i + 1].power == 0)
                        totalZeroCnt++;
                }
            }
            belts[N-1].robot = false;
            // 로봇을 인덱스 0위치에 올린다 (이때 올리는 위치에 내구도 -1)
            if (belts[0].power > 0) {
                belts[0].robot = true;
                belts[0].power--;
                if (belts[0].power == 0)
                    totalZeroCnt++;
            }
            // 내구도가 0인 곳이 K개 이상이면 종료
            if (totalZeroCnt >= K) {
                break;
            }
        }
        System.out.println(step);



    }

}

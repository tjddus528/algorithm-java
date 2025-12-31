import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] fish;
    static final int MAX_INT = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fish = new int[N+1][N+1];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            fish[0][i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        while(!isSuccess()) {
            // 1. 최소어항 개수에 +1
            pushToMin();
            // 2. 어항쌓기
            rotateBlocks();
            // 3. 인접한 어항에 대해 물고기 수 조절
            adjustBlocks();
            // 4. 일렬로 다시 놓기
            arrageBlocks();
            // 5. 공중부양 (절반, 180도 회전) 2번 반복
            foldBlocks();
            // 3. 인접한 어항에 대해 물고기 수 조절
            adjustBlocks();
            // 4. 일렬로 다시 놓기
            arrageBlocks();
            // 6. 최대와 최소 차이가 K이하가 될 때까지
            answer++;
        }
        System.out.println(answer);

    }

    static void pushToMin() {
        // 1. 최소어항 개수에 +1
        int min = MAX_INT;
        for(int i=0; i<N; i++) min = Math.min(min, fish[0][i]);
        for(int i=0; i<N; i++) if(fish[0][i]==min) fish[0][i]++;
    }

    static void rotateBlocks() {
        int width = 1, height = 1, start = 1;
        int index = 0;

        while(start + height - 1 < N) {
            index++;
            for(int y=0; y<height; y++) {
                for(int x=start-width; x<start; x++) {
                    fish[start-x][start+y] = fish[y][x];
                    fish[y][x] = 0;
                }
            }
            start += height;

            if(index % 2 == 0) width++;
            else height++;
        }
    }

    static void adjustBlocks() {
        int[][] temp = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                calcDiff(temp, i, j, i+1, j);
                calcDiff(temp, i, j, i, j+1);
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                fish[i][j] += temp[i][j];
            }
        }
    }

    static void calcDiff(int[][] temp, int n1, int p1, int n2, int p2) {
        if(fish[n1][p1]==0) return;
        if(fish[n2][p2]==0) return;

        int value = Math.abs(fish[n1][p1] - fish[n2][p2])/5;
        if(fish[n1][p1] > fish[n2][p2]) {
            temp[n1][p1] -= value;
            temp[n2][p2] += value;
        } else {
            temp[n2][p2] -= value;
            temp[n1][p1] += value;
        }
    }

    static void arrageBlocks() {
        int[] temp = new int[N];
        int idx = 0;
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                if(fish[y][x] != 0) {
                    temp[idx++] = fish[y][x];
                    fish[y][x]=0;
                }
            }
        }
        for(int i=0; i<N; i++) fish[0][i] = temp[i];
    }

    static void foldBlocks() {
        for(int i=0; i<N/4; i++) {
            fish[1][N-1-i] = fish[0][i];
            fish[0][i] = 0;
        }

        for(int i=0; i<N/4; i++) {
            fish[2][N/4*3+i] = fish[0][N/4+i];
            fish[0][N/4+i] = 0;
        }

        for (int i=0; i<N/4; i++) {
            fish[3][N-1-i] = fish[0][N/2+i];
            fish[0][N/2+i] = 0;
        }
    }

    static boolean isSuccess() {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(fish[i][j] == 0) continue;
                max = Math.max(max, fish[i][j]);
                min = Math.min(min, fish[i][j]);
            }
        }
        return max - min <= K;
    }
}

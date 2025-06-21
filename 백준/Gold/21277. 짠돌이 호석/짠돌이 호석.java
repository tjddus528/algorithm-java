import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] frame = new int[151][151]; // 전체 액자 크기
    static int[][] puzzle1 = new int[51][51]; // 퍼즐 1
    static int[][] puzzle2 = new int[51][51]; // 퍼즐 2

    static int n1, n2, m1, m2;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n1; i++) {
            String line = br.readLine();
            for(int j = 0; j < m1; j++) {
                puzzle1[i][j] = line.charAt(j) - '0';
            }
        }
        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n2; i++) {
            String line = br.readLine();
            for(int j = 0; j < m2; j++) {
                puzzle2[i][j] = line.charAt(j) - '0';
            }
        }

        // 퍼즐 2는 가운데 고정시켜두기
        for(int i = 50; i < 50 + n2; i++) {
            for(int j = 50; j < 50 + m2; j++) {
                frame[i][j] = puzzle2[i - 50][j - 50];
            }
        }

        for(int k = 0; k < 4; k++) {
            rotate();
            for(int i = 0; i < 100; i++) {
                for(int j = 0; j < 100; j++) {
                    go(i, j);
                }
            }
        }

        System.out.println(result);
    }
    static void rotate() {
        int[][] tmp = new int[51][51];

        //m1-1행부터 -> 0열로 복사
        for(int i = m1 - 1; i >= 0; i--) {
            for(int j = 0; j < n1; j++) {
                tmp[m1 - 1 - i][j] = puzzle1[j][i];
            }
        }

        for(int i = 0; i <= 50; i++) {
            for(int j = 0; j <= 50; j++) {
                puzzle1[i][j] = tmp[i][j];
            }
        }

        // swap(n1, m1)
        int temp = n1;
        n1 = m1;
        m1 = temp;
    }

    static void go(int y, int x) {
        boolean flag = true; // 만약 겹치는 숫자가 있다면 false

        for(int i = y; i < y + n1; i++) {
            for(int j = x; j < x + m1; j++) {
                if(frame[i][j] == 1 && puzzle1[i - y][j - x] == 1) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
        }

        if(flag) {
            // 넓이 계산
            int min_y = Math.min(y, 50);
            int max_y = Math.max(y + n1 - 1, 49 + n2);
            int min_x = Math.min(x, 50);
            int max_x = Math.max(x + m1 - 1, 49 + m2);

            int area = (max_y - min_y + 1) * (max_x - min_x + 1);
            result = Math.min(result, area);
        }
    }
}

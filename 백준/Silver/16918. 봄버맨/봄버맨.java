import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static char[][][] versionMap;		//1초, 3초, 5초 격자판 상태 저장 배열
    static int[] dr = { -1, 1, 0, 0 };	//상하좌우 y변경값
    static int[] dc = { 0, 0, -1, 1 };	//상하좌우 x변경값
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        versionMap = new char[3][R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++)
                versionMap[0][i][j] = str.charAt(j);
        }
        for(int i=0;i<2;i++){
            setting(i, i+1);
            fill(i+1);
        }
        
        if (N % 2 == 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++)
                    sb.append('O');
                sb.append("\n");
            }
        }else {	
            if (N == 1) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++)
                        sb.append(versionMap[0][i][j]);
                    sb.append("\n");

                }
            }else if (N % 4 == 3) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++)
                        sb.append(versionMap[1][i][j]);
                    sb.append("\n");
                }
            }else {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++)
                        sb.append(versionMap[2][i][j]);
                    sb.append("\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    static void setting(int v1, int v2){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (versionMap[v1][i][j] == 'O') {
                    versionMap[v2][i][j] = '.';
                    for (int d = 0; d < 4; d++) {
                        int tempR = i + dr[d];
                        int tempC = j + dc[d];
                        if (inSpace(tempR, tempC))
                            versionMap[v2][tempR][tempC] = '.';
                    }
                }
            }
        }
    }
    static void fill(int version){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (versionMap[version][i][j] == '.')
                    continue;
                versionMap[version][i][j] = 'O';
            }
        }
    }
    static boolean inSpace(int r, int c) {
        if (r >= 0 && c >= 0 && r < R && c < C)
            return true;
        return false;
    }
}
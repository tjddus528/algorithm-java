import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] chess;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chess = new int[N][M];
        for(int i=0; i<N; i++) {
            String[] colors = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                String color = colors[j];
                if (color.equals("W"))
                    chess[i][j] = 0;
                else
                    chess[i][j] = 1;
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<=N-8; i++) {
            for(int j=0; j<=M-8; j++) {
                answer = Math.min(answer, Math.min(check(i, j, 0), check(i, j, 1)));
            }
        }

        System.out.println(answer);

    }
    static int check(int x, int y, int first) {
        int cnt = 0;
        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                if(i%2==0){
                    if(j%2==0 && chess[i][j]!=first) cnt++;
                    if(j%2!=0 && chess[i][j]==first) cnt++;
                }
                else {
                    if(j%2==0 && chess[i][j]==first) cnt++;
                    if(j%2!=0 && chess[i][j]!=first) cnt++;
                }
            }
        }
        return cnt;
    }

}

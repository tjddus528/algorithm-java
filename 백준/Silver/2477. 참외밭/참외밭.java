import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 동 서 남 북
// 1  2  3  4
public class Main {
    static int K;
    static int[] len = new int[18];
    static int maxW, maxWIdx;
    static int maxH, maxHIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<6; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            if((d==1 || d==2) && l > maxW) {
                maxW = l;
                maxWIdx = 6+i;
            }
            if((d==3 || d==4) && l > maxH) {
                maxH = l;
                maxHIdx = 6+i;
            }
            len[i] = l;
            len[6+i] = l;
            len[12+i] = l;
        }

        int delW = Math.abs(len[maxWIdx-1]-len[maxWIdx+1]);
        int delH = Math.abs(len[maxHIdx-1]-len[maxHIdx+1]);
        int result = maxW*maxH - delW*delH;
        System.out.println(result * K);
    }
}

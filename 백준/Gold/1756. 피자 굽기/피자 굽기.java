import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] depth = new int[D+2];
        int[] pizza = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=D; i++) {
            depth[i] = Integer.parseInt(st.nextToken());
            if(i != 1) depth[i] = Math.min(depth[i], depth[i-1]);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) pizza[i] = Integer.parseInt(st.nextToken());

        int idx = D+1;
        for(int i=1; i<=N; i++) {
            while(idx > 0 && depth[idx] < pizza[i]) idx--;
            idx--;
            if(idx <= 0) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(idx+1);
    }

}
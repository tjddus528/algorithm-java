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
        for(int i=1; i<=D; i++) depth[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) pizza[i] = Integer.parseInt(st.nextToken());

        int min = depth[1];
        for(int i=1; i<=D; i++) {
            min = Math.min(min, depth[i]);
            depth[i] = min;
        }

        int first = 0;
        for(int i=1; i<=D+1; i++) {
            if(depth[i] < pizza[1]) {
                first = i-1;
                break;
            }
        }
        if(first == 0) {
            System.out.println(0);
            return;
        }
        int idx = first-1;
        int nth = 2;
        while(nth <= N && idx>0) {
            if(depth[idx] >= pizza[nth]) {
                idx--;
                nth++;
            }
            else {
                idx--;
            }
        }
        if(nth == N+1)
            System.out.println(idx + 1);
        else
            System.out.println(0);
    }

}
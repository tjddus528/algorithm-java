import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int T;
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int dist = y - x;
            int max = (int)Math.sqrt(dist);

            if(Math.sqrt(dist) == max) {
                System.out.println(2 * max - 1);
            } else if (dist <= max*(max+1)) {        // max*max < dist <= max*(max+1)
                System.out.println(2 * max);
            } else {
                System.out.println(2 * max + 1);
            }
        }
    }
}

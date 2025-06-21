import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int test;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(test --> 0) {
            N = Integer.parseInt(br.readLine());
            Set<Integer> note = new HashSet<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(N --> 0) {
                note.add(Integer.parseInt(st.nextToken()));
            }
            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while(M --> 0) {
                int num = Integer.parseInt(st.nextToken());
                sb.append(note.contains(num) ? 1+"\n": 0+"\n");
            }
        }
        System.out.println(sb);
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int T;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<String> numbers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                numbers.add(br.readLine());
            }

            Collections.sort(numbers);
            boolean consistency = true;
            for (int i = 0; i < N - 1; i++) {
                String cur = numbers.get(i);
                String next = numbers.get(i + 1);
                if (cur.length() > next.length())
                    continue;
                if (next.startsWith(cur)) {
                    consistency = false;
                    break;
                }
            }
            System.out.println(consistency ? "YES" : "NO");
        }
    }
}
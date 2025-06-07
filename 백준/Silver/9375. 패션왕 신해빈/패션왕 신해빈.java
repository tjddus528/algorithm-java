import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                String[] clothes = br.readLine().split(" ");
                String key = clothes[1];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            // map을 순차적으로 돌기
            int answer = 1;
            Iterator<Integer> iter = map.values().iterator();
            while (iter.hasNext()) {
                answer *= iter.next().intValue() + 1;
            }
            System.out.println(answer - 1);
        }
    }
}

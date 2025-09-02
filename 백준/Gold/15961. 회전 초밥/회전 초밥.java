import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 회전 초밥
public class Main {
    static int N, d, k, c;
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[2*N];
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            arr[i+N] = num;
        }
        map.put(c, 1);

        int s = 0;
        int e = k;
        for(int i=s; i<e; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        int result = map.size();
        
        while(s<N) {
            // s remove
            if(map.containsKey(arr[s])) {
                map.put(arr[s], map.get(arr[s])-1);
                if(map.get(arr[s])==0) map.remove(arr[s]);
            }
            // e+1 add
            map.put(arr[e], map.getOrDefault(arr[e], 0)+1);
            s++;
            e++;

            result = Math.max(result, map.size());
        }
        System.out.println(result);
    }

}

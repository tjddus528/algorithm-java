import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);

        /**
         * 비트마스킹
         */
        arr = new int[N+1];
        for(int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int x = -1;

            switch (a) {
                case 1:
                    x += Integer.parseInt(input[2]);
                    arr[b] |= (1 << x);
                    break;
                case 2:
                    x += Integer.parseInt(input[2]);
                    arr[b] &= ~(1 << x);
                    break;
                case 3:
                    arr[b] <<= 1;
                    // 20비트만 유지 (초과 비트 제거)
                    arr[b] &= (1 << 20) - 1;
                    break;
                case 4:
                    arr[b] >>= 1;
                    break;
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i=1; i <= N; i++) set.add(arr[i]);
        System.out.println(set.size());
    }

}

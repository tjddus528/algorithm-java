import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] A = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // 1) dist[i]: 0번 돌 → i번 돌까지의 minimax 힘
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        // 2) O(N^2)
        for (int i = 0; i < n; i++) {
            // 만약 i번 돌이 아직 도달 불가능 상태라면 건너뛰기
            if (dist[i] == Long.MAX_VALUE) continue;

            // i → j 점프 시 필요한 힘 계산
            for (int j = i + 1; j < n; j++) {
                long jumpCost = (long)(j - i) * (1 + Math.abs(A[i] - A[j]));
                long candidate = Math.max(dist[i], jumpCost);
                if (candidate < dist[j]) {
                    dist[j] = candidate;
                }
            }
        }

        // 3) 결과 출력
        System.out.println(dist[n - 1]);
    }
}
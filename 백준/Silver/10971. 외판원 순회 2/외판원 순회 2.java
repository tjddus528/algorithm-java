import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
    static int N;
    static int[][] arr;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            bt(i, 0, i, new boolean[N], 0);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close(); // 이 줄도 추가하면 좋습니다.
    }
    static void bt(int start, int depth, int index, boolean[] visited, int sum) {
        if(depth == N-1) {
            if(arr[index][start]==0) return;
            sum += arr[index][start];
            result = Math.min(result, sum);
            return;
        }

        visited[index] = true;
        for(int i=0; i<N; i++) {
            if(i == index) continue;
            if(visited[i]) continue;
            if(arr[index][i]==0) continue;
            bt(start, depth+1, i, visited, sum+arr[index][i]);
            visited[i] = false;
        }
    }
}


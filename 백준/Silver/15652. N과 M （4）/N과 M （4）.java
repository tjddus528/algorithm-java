import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M(4)
public class Main {
	static int N, M;
	static int[] output;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		output = new int[M];
		solve(0, 1);
	}
	// 중복 순열
	static void solve(int idx, int start) {
		if(idx >= M) {
			for(int value: output) System.out.print(value + " ");
			System.out.println();
			return;
		}
		for(int i=start; i<=N; i++) {
			output[idx] = i;
			solve(idx+1, i);
		}
	}

}

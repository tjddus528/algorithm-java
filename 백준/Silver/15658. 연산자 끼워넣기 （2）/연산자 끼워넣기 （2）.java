import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A;
	static int max = Integer.MAX_VALUE*(-1);
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		int[] oper = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) oper[i] = Integer.parseInt(st.nextToken());
		
		solve(1, oper, A[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void solve(int idx, int[] oper, int result) {
		if(idx >= N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i] <= 0) continue;
			
			int res = result;
			if(i==0) res += A[idx];
			else if(i==1) res -= A[idx];
			else if(i==2) res *= A[idx];
			else res /= A[idx];
			
			oper[i]--;
			solve(idx+1, oper, res);
			oper[i]++;
		}
	}

}

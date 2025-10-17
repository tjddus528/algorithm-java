import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

// ACM Craft
public class Main {
	static int T;
	static int N, K, W;
	static int[] indegree;
	static int[] DP;
	static int[] time;
	static ArrayList<ArrayList<Integer>> rules = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			indegree = new int[N+1];
			DP = new int[N+1];
			time = new int[N+1];
			for(int i=0; i<=N; i++) {
				rules.add(new ArrayList<Integer>());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) time[i] = Integer.parseInt(st.nextToken());
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				rules.get(X).add(Y);
				indegree[Y]++;
			}
			W = Integer.parseInt(br.readLine());
			BFS();
			System.out.println(DP[W]);
			rules.clear();
		}
	}
	
	static void BFS() {
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) {
				q.add(i);
				DP[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next: rules.get(cur)) {
				DP[next] = Math.max(DP[next], DP[cur]+time[next]);
				if(--indegree[next]==0)
					q.add(next);
			}
		}
	}
}

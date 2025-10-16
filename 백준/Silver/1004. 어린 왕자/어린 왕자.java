import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int N = Integer.parseInt(br.readLine());
			int[][] circles = new int[N][3];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				circles[i][0] = Integer.parseInt(st.nextToken());
				circles[i][1] = Integer.parseInt(st.nextToken());
				circles[i][2] = Integer.parseInt(st.nextToken());
			}
			
			int answer = 0;
			for(int i=0; i<N; i++) {
				// 출발점과 원 사이 거리
				int dist1 = (int)(Math.pow(x1-circles[i][0], 2)+Math.pow(y1-circles[i][1], 2));
				// 도착점과 원 사이 거리
				int dist2 = (int)(Math.pow(x2-circles[i][0], 2)+Math.pow(y2-circles[i][1], 2));
				
				int r = circles[i][2];
				// case1) 한 점은 원 밖, 한 점은 원 안에 존재
				if((dist1 > r*r && dist2 < r*r) || (dist1 < r*r && dist2 > r*r)) answer++;
				// case2) 두 점 모두 원 내부에 존재
				else if(dist1<r*r && dist2<r*r) continue;
				// case3) 두 점 모두 원 밖에 존재
				else if(dist1>r*r && dist2>r*r) continue;
				
			}
			System.out.println(answer);
		}
	}

}

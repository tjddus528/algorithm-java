import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 터렛
public class Main {
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			System.out.println(solve(x1, y1, r1, x2, y2, r2));
		
		}
	}
	
	static int solve(int x1, int y1, int r1, int x2, int y2, int r2) {
		int distance = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
		
		// case1) 좌표와 거리가 동일, 무한 경우의 수
		if(x1==x2 && y1==y2 && r1==r2) return -1;
		
		// case2) 접점이 없을 때
		// 2-1) 하나의 원이 내부에 존재, 접점없음
		else if(distance < (r1-r2)*(r1-r2)) return 0;
		
		// 2-2) 원이 서로 외부에 존재, 접점없음
		else if(distance > (r1+r2)*(r1+r2)) return 0;
		
		// case3) 접점이 하나
		// 3-1) 내접
		else if(distance == (r1-r2)*(r1-r2)) return 1;
		
		// 3-2) 외접
		else if(distance == (r1+r2)*(r1+r2)) return 1;
		
		// case4) 그 외 접점 2개
		else return 2;
	}
}

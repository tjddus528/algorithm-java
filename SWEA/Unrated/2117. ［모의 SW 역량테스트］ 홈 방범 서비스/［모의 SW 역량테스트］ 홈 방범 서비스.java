import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 홈 방범 서비스
public class Solution {
    static int N, M, result;
    static ArrayList<House> houseList = new ArrayList<>();
    static class House {
        int x, y;
        House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            result = 0;
            houseList.clear();
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int c = Integer.parseInt(st.nextToken());
                    if(c == 1) 
                        houseList.add(new House(i, j));
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    for(int k=1; k<=2*N; k++) {
                        int cost = k*k + (k-1)*(k-1);
                        int cnt = 0;
                        for(House house: houseList) {
                            int dist = Math.abs(house.x - i) + Math.abs(house.y - j);
                            if(dist < k) cnt++; 
                        }
                        int ans = M * cnt - cost;
                        if(ans >= 0) result = Math.max(result, cnt);
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}

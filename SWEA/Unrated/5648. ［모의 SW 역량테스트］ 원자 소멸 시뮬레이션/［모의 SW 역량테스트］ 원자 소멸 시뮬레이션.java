import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    // 원자
    static class Atom implements Comparable<Atom> {
        int x, y, dir, e;
        Atom(int _x, int _y, int _dir, int _e) {
            this.x = _x;
            this.y = _y;
            this.dir = _dir;
            this.e = _e;
        }
        // x좌표 기준 오름차순, y좌표 기준 오름차순 => 방향을 고려한 충돌 여부를 확인하기 위해
         @Override
         public int compareTo(Atom o) {
            // 나의 x좌표가 상대의 x좌표보다 작은 순, x좌표가 같다면 y좌표가 작은 순
            return this.x < o.x ? -1 : (this.x != o.x ? Integer.compare(this.x, o.x) : Integer.compare(this.y, o.y));
         }
    }
    // 충돌리스트에 담을 충돌하는 두 원자와 그때의 시간 정보
    static class Pair implements Comparable<Pair> {
        int i, j, time;
        Pair(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }
        @Override
        public int compareTo(Pair o) {
            // 충돌 시간 오름차순 정렬
            return Integer.compare(this.time, o.time);
        }
    }
    static int N;
    static ArrayList<Atom> atomList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());    // 원자 개수
            atomList = new ArrayList<>();
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())*2;
                int y = Integer.parseInt(st.nextToken())*2;
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                atomList.add(new Atom(x, y, dir, e));
            }
            sb.append("#").append(t).append(" ").append(makeBoomPair()).append("\n");

        }
        System.out.println(sb.toString());
    }

    static int makeBoomPair() {
        // 원자 리스트를 좌표 기준으로 정렬
        Collections.sort(atomList);
        ArrayList<Pair> boomList = new ArrayList<>();

        // 원자 2개씩 조합 생성 (개수가 2개 고정이면 재귀로 안 짜도 될 듯)
        for(int i=0; i<N; i++) {    // 선택할 첫번째 원자
            for(int j=i+1; j<N; j++) {  // 선택할 두번째 원자
                Atom a = atomList.get(i);
                Atom b = atomList.get(j);

                // 수직선에서 만날 때 : 같은 x 좌표를 갖는 원자들
                if(a.x == b.x) {
                    if(a.dir == 0 && b.dir == 1) {
                        int gap = (b.y - a.y)/2;
                        boomList.add(new Pair(i, j, gap));
                    }
                }
                // 수평선에서 만날 때 : 같은 y 좌표를 갖는 원자들
                else if (a.y == b.y) {
                    if(a.dir == 3 && b.dir == 2) {
                        int gap = (b.x - a.x)/2;
                        boomList.add(new Pair(i, j, gap));
                    }
                }
                // 대각선 라인(/) : 기울기가 1이어야 함
                else if (a.x-a.y == b.x-b.y) {
                    if((a.dir == 0 && b.dir == 2) || (a.dir == 3 && b.dir == 1)) {
                        int gap = Math.abs(b.x - a.x);
                        boomList.add(new Pair(i, j, gap));
                    }
                }
                // 대각선 라인(\) : 기울기가 -1이어야 함
                else if (a.x + a.y == b.x + b.y) {
                    if((a.dir == 1 && b.dir == 2) || (a.dir == 3 && b.dir == 0)) {
                        int gap = Math.abs(b.x - a.x);
                        boomList.add(new Pair(i, j, gap));
                    }
                }
            }
        }
        
        return getTotalEnerge(boomList);
    }

    static int getTotalEnerge(ArrayList<Pair> boomList) {
        // 충돌 시간 기준 오름차순 정렬
        Collections.sort(boomList);

        // 원자별로 충돌 시간 저장(가장 빠른 충돌 시간만 저장됨 -> 충돌되면 사라지기 때문)
        int INF = Integer.MAX_VALUE;
        int[] boomTimes = new int[N];
        Arrays.fill(boomTimes, INF);

        int sum = 0;
        for(Pair p: boomList) {

            // 둘 중에 하나라도 이미 터진 원자라면
            if(boomTimes[p.i] < p.time || boomTimes[p.j] < p.time) continue;
            
            // 충돌처리 : 현재 충돌 시간으로 갱신하고, 에너지 누적
            if(boomTimes[p.i] == INF) {
                boomTimes[p.i] = p.time;
                sum += atomList.get(p.i).e;
            }
            if(boomTimes[p.j] == INF) {
                boomTimes[p.j] = p.time;
                sum += atomList.get(p.j).e;
            }
        
        }
        return sum;
    }

}

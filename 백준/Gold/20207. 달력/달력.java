import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] plans;
    static class Square {
        int start;
        int end;
        int height;
        PriorityQueue<Integer> endPQ = new PriorityQueue<>();

        Square(int s, int e, int h) {
            this.start = s;
            this.end = e;
            this.height = h;
            endPQ.add(e);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        plans = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            plans[i][0] = Integer.parseInt(st.nextToken());
            plans[i][1] = Integer.parseInt(st.nextToken());
        }

        // sort (시작일-오름차순, 종료일-내림차순) -> O(NlogN)
        Arrays.sort(plans, (a,b) -> {
            if(a[0] == b[0]) return b[1]-a[1];
            return a[0]-b[0];
        });

        // 일정들을 하나씩 탐색하면서 (기존 종료일 vs 다음 일정 시작일)을 비교 -> 최대 O(N^2)
        // 코팅지 크기는 클래스의 리스트로 관리
        List<Square> squares = new ArrayList<>();
        for(int i=0; i<N; i++) {
            int start = plans[i][0];
            int end =   plans[i][1];
            updateSquare(squares, start, end);
        }

        // 코팅지 넓이 계산 -> 최대 O(N)
        int area = 0;
        for(Square s: squares) {
            area += s.endPQ.size() * (s.end - s.start + 1);
        }
        System.out.println(area);
    }
    static void updateSquare(List<Square> squares, int start, int end) {
        for(Square sq: squares) {
            // 기존 일정과 연속된 일정일 경우
            if(start <= sq.end+1) {
                // 기존 일정의 최소 종료일보다 나중일 경우 종료일 갱신
                if(!sq.endPQ.isEmpty() && sq.endPQ.peek() < start) {
                    sq.endPQ.poll();
                    sq.endPQ.add(end);
                }
                // 새로운 줄의 종료일 추가
                else sq.endPQ.add(end);

                // 연속 종료일 갱신
                sq.end = Math.max(sq.end, end);
                return;
            }
        }
        // 기존 일정과 연속된 일정이 아닌 경우
        squares.add(new Square(start, end, 1));
    }

}

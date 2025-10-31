import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> cupNoodle = new PriorityQueue<>((o1, o2) -> {
            if(o1[0]==o2[0]) return o2[1]-o1[1];
            else return o1[0]-o2[0];
        });
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            cupNoodle.offer(new int[]{deadLine, cnt});
        }

        PriorityQueue<Integer> select = new PriorityQueue<>();

        while(!cupNoodle.isEmpty()) {
            int[] cur = cupNoodle.poll();
            // 문제를 푼 시간(현재 날짜)보다 아직 데드라인이 크면 : 문제를 풀 수 있다
            if(select.size() < cur[0]) {
                select.offer(cur[1]);
            }
            // 문제를 풀 수 없다
            // -> 이때 지금까지 문제를 풀었던 경우보다 컵라면을 많이 얻을 수 있었다면 교체한다
            else {
                if(!select.isEmpty() && select.peek() < cur[1]) {
                    select.poll();
                    select.offer(cur[1]);
                }
            }
        }
        long answer = 0;
        while(!select.isEmpty()) {
            answer += select.poll();
        }
        System.out.println(answer);

    }

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Set<Integer> breaks = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if(M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) breaks.add(Integer.parseInt(st.nextToken()));
        }


        int answer = 0;
        // case1) 채널 100에서 +, - 로 이동
        answer = Math.abs(100 - N);
        // case2) 새로운 채널로 이동해서 +, -로 이동
        // 새로운 채널 : 버튼으로 만들 수 있는 모든 숫자 (최대 1_000_000)
        for(int ch=0; ch<=1_000_000; ch++) {
            int cnt = possibleCnt(ch);
            if(cnt > 0) {
                answer = Math.min(answer, Math.abs(ch - N) + cnt);
            }
        }
        System.out.println(answer);

    }

    static int possibleCnt(int ch) {
        int cnt = 0;
        if (ch == 0) return breaks.contains(0) ? 0 : 1;

        while(ch > 0) {
            if(breaks.contains(ch%10)) return 0;
            ch /= 10;
            cnt++;
        }
        return cnt;
    }

}

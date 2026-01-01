import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static String[] words;
    static boolean[] alpha = new boolean[26];
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new String[N];
        for(int i=0; i<N; i++) words[i] = br.readLine();

        // a, n, t, i, c 는 무조건 알아야 함
        if (K < 5) {
            System.out.println(0);
            return;
        }
        alpha[0] = true;
        alpha['n'-'a'] = true;
        alpha['t'-'a'] = true;
        alpha['i'-'a'] = true;
        alpha['c'-'a'] = true;

        onlyK(0, 5);
        System.out.println(answer);
    }

    static void onlyK(int now, int cnt) {
        if(cnt >= K) {
            int sum = 0;
            for(int i=0; i<N; i++) {
                boolean read = true;
                for(int j=0; j<words[i].length(); j++) {
                    int ch = words[i].charAt(j);
                    if(!alpha[ch-'a']) {
                        read = false;
                        break;
                    }
                }
                if(read) sum++;
            }
            answer = Math.max(answer, sum);
            return;
        }
        for(int i=now; i<26; i++) {
            if(i==0 || i==('n'-'a') || i==('t'-'a') || i==('i'-'a') || i==('c'-'a')) continue;
            alpha[i] = true;
            onlyK(i+1, cnt+1);
            alpha[i] = false;
        }
    }

}

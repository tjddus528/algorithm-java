import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

import static java.lang.Integer.compare;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Problem, Integer> map = new TreeMap<>((p1, p2) -> {
            if (p1.score == p2.score) return compare(p1.num, p2.num);

            return compare(p1.score, p2.score);
        });
        TreeMap<Integer, Problem> map2 = new TreeMap<>();

        int N = parseInt(br.readLine());

        Problem p;
        int score, num;
        StringTokenizer st;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            num = parseInt(st.nextToken());
            score = parseInt(st.nextToken());

            p = new Problem(score, num);
            map.put(p, num);
            map2.put(num, p);
        }


        int M = parseInt(br.readLine());
        Problem key;
        String cmd;
        int x;
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            switch (cmd) {
                case "add":
                    num = parseInt(st.nextToken());
                    score = parseInt(st.nextToken());
                    p = new Problem(score, num);
                    map.put(p, num);
                    map2.put(num, p);
                    break;
                case "solved":
                    num = parseInt(st.nextToken());
                    p = map2.get(num);
                    map.remove(p);
                    break;
                case "recommend":
                    x = parseInt(st.nextToken());
                    key = x == -1 ? map.firstKey() : map.lastKey();
                    sb.append(map.get(key)).append("\n");
                    break;
            }
        }

        System.out.print(sb);
        br.close();
    }

    static class Problem {
        int score, num;

        public Problem(int score, int num) {
            this.score = score;
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem = (Problem) o;
            return score == problem.score && num == problem.num;
        }
    }
}

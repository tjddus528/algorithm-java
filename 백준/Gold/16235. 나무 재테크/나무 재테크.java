import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    static int N, M, K;
    static int[][] ground;
    static int[][] A;
    static List<int[]> tree;
    static List<int[]> death;
    static boolean[] isDeath;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        K = Integer.parseInt(data[2]);
        ground = new int[N+1][N+1];
        for(int i=1; i<=N; i++) Arrays.fill(ground[i], 5);
//        System.out.println(Arrays.deepToString(ground));
        A = new int[N+1][N+1];
        tree = new ArrayList<>();
        death = new ArrayList<>();
        isDeath = new boolean[M];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            tree.add(new int[]{x, y, age, 1});
        }

        // tree : 나이 어린 순으로 정렬
        while(K --> 0) {
            tree.sort(Comparator.comparing(a -> a[2]));
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(tree.size());



    }
    static void spring() {
        List<int[]> survivors = new ArrayList<>();
        death.clear();  // 지난 해 죽은 거 남아있지 않게

        for (int[] t : tree) {
            int x = t[0], y = t[1], age = t[2];
            if (ground[x][y] >= age) {
                ground[x][y] -= age;
                t[2] = age + 1;
                survivors.add(t);
            } else {
                death.add(t);
            }
        }
        tree = survivors;  // 죽은 나무 제거
    }
    static void summer() {
        for(int[] t: death) {
            int x = t[0];
            int y = t[1];
            int age = t[2];
            ground[x][y] += (age/2);
        }
        death.clear();
    }
    static void fall() {
        int[] dx = new int[]{-1,-1,-1, 0, 0, 1, 1, 1};
        int[] dy = new int[]{-1, 0, 1,-1, 1,-1, 0, 1};
        List<int[]> newTrees = new ArrayList<>();
        for (int[] t: tree) {
            if (t[2] % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int nx = t[0] + dx[d];
                    int ny = t[1] + dy[d];
                    if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                    newTrees.add(new int[]{nx, ny, 1});
                }
            }
        }
        tree.addAll(newTrees);
    }
    static void winter() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }
}


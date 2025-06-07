import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N, M;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        arr = new int[N+1][21];
        for(int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = 0;
            if(a==1 || a==2) c = Integer.parseInt(input[2]);

            if(a==1) arr[b][c] = 1;
            if(a==2) arr[b][c] = 0;
            if(a==3) {
                for(int j=20; j>=2; j--) {
                    arr[b][j] = arr[b][j-1];
                }
                arr[b][1] = 0;
            }
            if(a==4) {
                for(int j=1; j<=19; j++) {
                    arr[b][j] = arr[b][j+1];
                }
                arr[b][20] = 0;
            }
        }
        Set<String> train = new HashSet<>();
        for(int i=1; i<=N; i++) {
            String code = "";
            for(int j=1; j<=20; j++) {
                code += String.valueOf(arr[i][j]);
            }
            train.add(code);
        }
        System.out.println(train.size());

    }

}

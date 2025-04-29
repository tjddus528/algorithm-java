import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = i+1;
        }

        Pair[] pairs = new Pair[M];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair(a, b);
        }
        Arrays.sort(pairs);

        for(int i=0; i<pairs.length; i++){
            int a = pairs[i].a;
            int b = pairs[i].b;
            for(int j=a-1; j<b; j++){
                arr[j] = arr[a-1];
            }
        }

        int t = arr[0];
        int ans = 1;
        for(int i=0; i<N; i++){
            if(t != arr[i]){
                ans++;
                t = arr[i];
            }
        }
        bw.write(ans+" ");
        bw.close();
    }
}
class Pair implements Comparable<Pair>{
    int a, b;
    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
    public int compareTo(Pair p){
        return this.a - p.a;
    }
}
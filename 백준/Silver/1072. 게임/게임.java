import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int Z = getZ(X, Y);

        long s = 0;
        long e = 1_000_000_000_000L;
        while(s < e) {
            long mid = (s+e)/2;
            int zz = getZ(X+mid, Y+mid);
            if(zz > Z) {
                e = mid;
            } else if (zz == Z) {
                s = mid+1;
            }
        }
        if (getZ(X + s, Y + s) != Z) {
            System.out.println(s);
        } else {
            System.out.println(-1);
        }
    }

    static int getZ(long x, long y) {
        return (int)(y*100/x);
    }
}

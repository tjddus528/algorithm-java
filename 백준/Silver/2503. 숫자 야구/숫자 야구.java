import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] check = new boolean[1000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=123; i<1000; i++) {
            String str = ""+i;
            if(str.charAt(0) == '0' || str.charAt(1)=='0' || str.charAt(2) == '0') continue;
            if(str.charAt(0) == str.charAt(1) || str.charAt(0) == str.charAt(2) || str.charAt(1) == str.charAt(2)) continue;
            check[i] = true;
        }

        for(int i=0; i<n; i++) {
            String[] data = br.readLine().split(" ");
            String num = data[0];
            int s = Integer.parseInt(data[1]);
            int b = Integer.parseInt(data[2]);
            removeCandi(num, s, b);
        }

        int cnt = 0;
        for(boolean b: check)
            if(b) cnt++;
        System.out.println(cnt);

    }
    static void removeCandi(String num, int s, int b) {

        for(int candi = 123; candi < 1000; candi++) {
            if(!check[candi]) continue;
            String cd = ""+candi;
            int strike = 0;
            int ball = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(cd.charAt(i) == num.charAt(j)) {
                        if(i == j) strike++;
                        else ball++;
                        break;
                    }
                }
            }
            if(strike == s && ball == b) check[candi] = true;
            else check[Integer.parseInt(cd)] = false;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int bar = 64;
        int cnt = 0;
        int temp = bar;
        while(X > 0) {
            if(X < temp) {
                temp /= 2;
            }
            else {
                X -= temp;
                cnt++;
                temp = bar;
            }
        }
        System.out.println(cnt);
    }

}

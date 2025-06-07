import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String pb = br.readLine();
        int cnt = 1;
        char first = pb.charAt(0);
        char prev = first;
        for(int i=1; i<pb.length(); i++) {
            char cur = pb.charAt(i);
            if(prev != cur && first != cur) cnt++;
            prev = cur;
        }
        System.out.println(cnt);
    }

}

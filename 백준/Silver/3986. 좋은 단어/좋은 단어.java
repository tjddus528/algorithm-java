import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i=0; i<n; i++) {
            if(check(br.readLine())) cnt++;
        }
        System.out.println(cnt);
    }
    static boolean check(String str) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(stack.isEmpty() || stack.peek() != c) {
                stack.add(c);
            }
            else stack.pop();
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}

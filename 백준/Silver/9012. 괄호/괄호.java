import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    while(n-->0) {
      String str = br.readLine();
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == '(') {
          stack.add(str.charAt(i));
        } else {
          if (stack.isEmpty()) {
            stack.add(str.charAt(i));
            break;
          }
          else stack.pop();
        }
      }
      if (!stack.isEmpty()) {
        System.out.println("NO");
      } else {
        System.out.println("YES");
      }
    }
  }
}

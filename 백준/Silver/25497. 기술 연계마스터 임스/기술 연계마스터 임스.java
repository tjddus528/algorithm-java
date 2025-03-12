import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String play = br.readLine();
    Stack<Character> SK = new Stack<>();
    Stack<Character> LR = new Stack<>();
    int cnt = 0;
    for(int i=0; i<n; i++) {
      if(play.charAt(i) >= '1' && play.charAt(i) <= '9') cnt++;
      if(play.charAt(i) == 'S') SK.add('S');
      if(play.charAt(i) == 'L') LR.add('L');
      if(play.charAt(i) == 'K') {
        if(!SK.isEmpty()) {
          SK.pop();
          cnt++;
        } else {
          break;
        }
      }
      if(play.charAt(i) == 'R') {
        if(!LR.isEmpty()) {
          LR.pop();
          cnt++;
        } else {
          break;
        }
      }
    }
    System.out.println(cnt);
  }
}

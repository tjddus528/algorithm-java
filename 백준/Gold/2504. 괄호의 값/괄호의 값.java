import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        List<Character> init = new ArrayList<>();
        for(int i=0; i<input.length(); i++) init.add(input.charAt(i));
        System.out.println(dfs(init));

    }
    static int dfs(List<Character> str) {
        if(str.isEmpty()) return 1;

        Stack<Character> stack = new Stack<>();
        int total = 0;
        int s=0;
        for (int i=0; i<str.size(); i++) {
            if(stack.isEmpty()) {
                stack.add(str.get(i));
                continue;
            }
            else {
                if (str.get(i) == ')' && stack.peek() == '(') stack.pop();
                else if (str.get(i) == ']' && stack.peek() == '[') stack.pop();
                else stack.add(str.get(i));
            }

            if(stack.isEmpty()) {
                if(str.get(i) == ')') total += 2*dfs(subStr(str, s+1, i));
                else if(str.get(i) == ']') total += 3*dfs(subStr(str, s+1, i));
                s = i+1;
            }
        }
        if(!stack.isEmpty()) return 0;
        return total;
    }
    static List<Character> subStr(List<Character> origin, int s, int e) {
        List<Character> next = new ArrayList<>();
        for(int i=s; i<e; i++) {
            next.add(origin.get(i));
        }
        return next;
    }
}

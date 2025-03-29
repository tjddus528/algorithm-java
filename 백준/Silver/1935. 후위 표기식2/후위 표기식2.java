import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String str = br.readLine();
    double[] num = new double[n];
    for(int i=0; i<n; i++) {
      num[i] = Integer.parseInt(br.readLine());
    }

    Stack<Double> stack = new Stack<>();
    for(int i=0; i<str.length(); i++) {
      if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
        stack.add(num[str.charAt(i)-'A']);
      }
      else {
        double b = stack.pop();
        double a = stack.pop();
        stack.add(calc(a, b, str.charAt(i)));
      }
    }
    double answer = stack.pop();
    System.out.println(String.format("%.2f", answer));
  }
  static double calc(double a, double b, char oper) {
    switch (oper) {
      case '+': return a+b;
      case '-': return a-b;
      case '/': return a/b;
      case '*': return a*b;
      default: return 0;
    }
  }

}

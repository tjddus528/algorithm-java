import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    double answer;
    switch (str) {
      case "A+": {
        answer = 4.3;
        break;
      }
      case "A0": {
        answer = 4.0;
        break;
      }
      case "A-": {
        answer = 3.7;
        break;
      }
      case "B+": {
        answer = 3.3;
        break;
      }
      case "B0": {
        answer = 3.0;
        break;
      }
      case "B-": {
        answer = 2.7;
        break;
      }
      case "C+": {
        answer = 2.3;
        break;
      }
      case "C0": {
        answer = 2.0;
        break;
      }
      case "C-": {
        answer = 1.7;
        break;
      }
      case "D+": {
        answer = 1.3;
        break;
      }
      case "D0": {
        answer = 1.0;
        break;
      }
      case "D-": {
        answer = 0.7;
        break;
      }
      default: answer = 0.0;

    }
    System.out.println(answer);
  }
}

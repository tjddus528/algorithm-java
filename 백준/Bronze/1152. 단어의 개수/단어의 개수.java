import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] str = br.readLine().split(" ");
    int answer = str.length;
    if(str.length > 0) {
      if (str[0].equals(""))
        answer--;
      if (str[str.length - 1].equals(""))
        answer--;
    }
    System.out.println(answer);
  }
}

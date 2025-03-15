import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int number = 665;
    while (n>0) {
      number++;
      if(check(String.valueOf(number))) n--;
    }
    System.out.println(number);
  }

  static boolean check(String name) {
    for(int i=0; i<name.length()-2; i++) {
      if(name.charAt(i)=='6' && name.charAt(i+1)=='6' && name.charAt(i+2)=='6') {
        return true;
      }
    }
    return false;
  }
}

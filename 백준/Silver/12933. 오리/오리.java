import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static char[] quack = {'q','u','a','c','k'};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split("");
    int n = data.length;
    char[] records = new char[n];
    for(int i=0; i<n; i++) {
      records[i] = data[i].charAt(0);
    }
    int total = 0;
    int duck = 0;
    while(total < n) {
      int idx = 0;
      boolean possible = false;
      for(int i=0; i<n; i++) {
        if(records[i] == quack[idx]) {
          records[i] = 'X';
          if(idx == 4) {
            total += 5;
            possible = true;
          }
          idx = (idx+1)%5;
        }
      }
      if(possible) duck++;
      if(!possible) break;
    }
    System.out.println(total!=n?-1:duck);
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int[] index = new int[26];
    for(int i=0; i<26; i++) index[i] = -1;
    for(int i=0; i<str.length(); i++) {
      int k = str.charAt(i)-'a';
      if(index[k] < 0) index[k] = i;
    }
    for(int i=0; i<26; i++)
      System.out.print(index[i]+" ");
  }
}

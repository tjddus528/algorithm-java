import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    HashMap<Character, Integer> dict = new HashMap<>();
    for(int i=0; i<str.length(); i++) {
      char c = Character.toUpperCase(str.charAt(i));
      if(!dict.containsKey(c))
        dict.put(c, 1);
      else
        dict.put(c, dict.get(c)+1);
    }
    int max = 0;
    for(int value: dict.values()) {
      max = Math.max(max, value);
    }
    char answer = '?';
    for(char key: dict.keySet()) {
      if(dict.get(key) == max) {
        if(answer == '?') {
          answer = key;
        } else {
          answer = '?';
          break;
        }
      }
    }
    System.out.println(answer);
  }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int x = Integer.parseInt(br.readLine());

    Set<Integer> set = new HashSet<>();
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{x, 0});
    set.add(x);
    int result = 0;
    while(!q.isEmpty()) {
      int[] num = q.poll();
      if(num[0]==1) {
        result = num[1];
        break;
      }
      if(num[0] % 3==0 && !set.contains(num[0]%3)) q.offer(new int[]{num[0]/3, num[1]+1});
      if(num[0] % 2==0 && !set.contains(num[0]%2)) q.offer(new int[]{num[0]/2, num[1]+1});
      if(!set.contains(num[0]-1)) q.offer(new int[]{num[0]-1, num[1]+1});
    }
    System.out.println(result);
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int h = Integer.parseInt(data[1]);
    int t = Integer.parseInt(data[2]);
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for(int i=0; i<n; i++) {
      pq.add(Integer.parseInt(br.readLine()));
    }

    int count = 0;
    for(int i=0; i<t; i++) {
      int max = pq.poll();
      if(max < h || max==1) {
        pq.offer(max);
        break;
      }
      else {
        pq.offer((int) (double) (max / 2));
        count++;
      }
    }
    if (!pq.isEmpty() && pq.peek() >= h) {
      System.out.println("NO");
      System.out.println(pq.peek());
    } else {
      System.out.println("YES");
      System.out.println(count);
    }

  }
}

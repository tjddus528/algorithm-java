import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        pq.offer(Integer.parseInt(st.nextToken()));
      }
    }
    int result = 0;
    for(int i=0; i<n; i++) {
      result = pq.poll();
    }
    System.out.println(result);
  }

}

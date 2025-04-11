import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    List<Integer> crane = new ArrayList<>();
    for(int i=0; i<n; i++) {
      crane.add(Integer.parseInt(st.nextToken()));
    }
    int m = Integer.parseInt(br.readLine());
    List<Integer> box = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<m; i++) {
      box.add(Integer.parseInt(st.nextToken()));
    }

    // 내림차순 정렬
    Collections.sort(crane, Collections.reverseOrder());
    Collections.sort(box, Collections.reverseOrder());

    if (box.get(0) > crane.get(0)) {
      System.out.println(-1);
      return;
    }

    int time = 0;
    while(!box.isEmpty()) {
      int boxIdx = 0, craneIdx = 0;
      while(craneIdx<n) {
        if(boxIdx == box.size()) break;
        if (crane.get(craneIdx) >= box.get(boxIdx)) {
          box.remove(boxIdx);
          craneIdx++;
        }
        else boxIdx++;
      }
      time++;
    }
    System.out.println(time);
  }
}
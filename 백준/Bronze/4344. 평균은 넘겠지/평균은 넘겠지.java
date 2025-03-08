import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine());
    while(test-->0) {
      String[] data = br.readLine().split(" ");
      int n = Integer.parseInt(data[0]);
      int[] score = new int[n];
      int total = 0;
      for (int i = 0; i < n; i++) {
        score[i] = Integer.parseInt(data[i + 1]);
        total += score[i];
      }
      double avg = (double) total / n;
      int cnt = 0;
      for (int i = 0; i < n; i++) {
        if (score[i] > avg)
          cnt++;
      }
      System.out.printf("%.3f", (double) cnt / n * 100);
      System.out.println("%");
    }
  }
}
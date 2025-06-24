import java.io.*;
import java.util.*;
 
public class Main {
   public static void main(String[] args) throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(in.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken()) * 2 + 1;
 
      int[] ice = new int[1000001];
      int g, x;
      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(in.readLine());
         g = Integer.parseInt(st.nextToken());
         x = Integer.parseInt(st.nextToken());
         ice[x] = g;
      }
 
      int sum = 0, max = 0;
      for (int i = 0; i <= 1000000; i++) {
         if(i-K>=0) sum -= ice[i - K];
         sum += ice[i];
         max = Math.max(max,sum);
      }
 
      System.out.println(max);
   }
}
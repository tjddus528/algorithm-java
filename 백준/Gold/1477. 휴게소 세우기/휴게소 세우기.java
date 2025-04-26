import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    int l = Integer.parseInt(data[2]);
    int[] arr = new int[n+2];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
    arr[n+1] = l;
    Arrays.sort(arr);
    int left = 1;
    int right = l-1;
    while(left <= right) {
      int mid = (left+right)/2;   // 휴게소 사이 구간의 후보
      int total = 0;
      for(int i=1; i<=n+1; i++) {
        total += (arr[i]-arr[i-1]-1)/mid;
      }
      if(total <= m) right = mid-1;
      else left = mid+1;
    }
    System.out.println(left);
  }
}

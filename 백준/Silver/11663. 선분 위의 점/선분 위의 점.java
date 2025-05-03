import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int n, m;
  static int[] arr;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Integer.parseInt(data[0]);
    m = Integer.parseInt(data[1]);
    arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    Arrays.sort(arr);
    for(int i=0; i<m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (a > arr[n - 1]) {
        System.out.println(0);
        continue;
      }
      if (b < arr[0]) {
        System.out.println(0);
        continue;
      }

      int aIdx = binary_search_upper(a);
      int bIdx = binary_search_under(b);
      System.out.println(aIdx<=bIdx?bIdx-aIdx+1:0);
    }
  }
  // arr에서 num보다 같거나 큰 숫자의 idx를 반환
  static int binary_search_upper(int num) {
    int left = 0;
    int right = n-1;
    int result = right;

    while(left <= right) {
      int mid = (left+right)/2;
      if(arr[mid] >= num) {
        result = mid;
        right = mid-1;
      }
      else
        left = mid + 1;
    }
    return result;
  }
  // arr에서 num보다 같거나 작은 숫자의 idx를 반환
  static int binary_search_under(int num) {
    int left = 0;
    int right = n-1;
    int result = left;

    while(left <= right) {
      int mid = (left+right)/2;
      if(arr[mid] <= num) {
        result = mid;
        left = mid + 1;
      }
      else
        right = mid-1;
    }
    return result;
  }
}

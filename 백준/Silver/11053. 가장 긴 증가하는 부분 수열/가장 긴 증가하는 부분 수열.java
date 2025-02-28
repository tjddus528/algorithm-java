import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] output = new int[n];

    int idx = 0;
    output[0] = arr[0];
    for(int i=1; i<arr.length; i++) {
      if(output[idx] < arr[i]) {
        output[idx+1] = arr[i];
        idx++;
      }
      else {
        int k = binarySearch(output, 0, idx, arr[i]);
        output[k] = arr[i];
      }
    }
    System.out.println(idx+1);
  }
  static int binarySearch(int[] arr, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;
      if (arr[mid] >= target)
        right = mid;
      else
        left = mid + 1;
    }
    return right;
  }
}

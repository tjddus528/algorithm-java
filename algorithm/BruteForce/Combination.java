package algorithm.BruteForce;

import java.util.Arrays;
public class Combination {
  static int[] arr;
  static int[] output;
  static boolean[] visited;
  static int n, r;

  public static void main(String[] args) {
    n = 3;
    r = 2;
    arr = new int[]{1, 2, 3};
    output = new int[r];
    visited = new boolean[n];

    combi(0, 0, n, r);
    combi_with_bt(0, 0, n, r);
    combi_with_recursion(0, 0, n, r);

  }


  private static void combi(int start, int depth, int n, int r) {
    if(depth == r) {
      System.out.println(Arrays.toString(output));
      return;
    }
    for(int i=start; i<n; i++){
      output[depth] = arr[i];
      combi(i+1, depth+1, n, r);
    }
  }

  // 사실상 첫번째랑 크게 다르지는 않음
  private static void combi_with_bt(int start, int depth, int n, int r) {
    if(r==0) {
      System.out.println(Arrays.toString(output));
      System.out.println(Arrays.toString(visited)); // -> visited를 활용해서 결과 출력
      return;
    }

    for(int i=start; i<n; i++) {
      visited[i] = true;
      output[depth] = arr[i];
      combi_with_bt(i+1, depth+1, n, r-1);
      visited[i] = false;
    }

  }


  // count는 몇 개를 선택했는지를 나타냄
  private static void combi_with_recursion(int count, int depth, int n, int r) {
    // r개만큼 모두 선택했을 경우 return
    if (count == r) {
      for(int i=0; i<n; i++) if(visited[i]) System.out.print(arr[i]+" ");
      System.out.println();
      return;
    }
    // 배열의 마지막까지 도달했을 경우 return
    if (depth == n) return;

    // 현재 요소를 선택할 경우
    visited[depth] = true;
    combi_with_recursion(count+1, depth+1, n, r);

    // 현재 요소를 선택하지 않은 경우
    visited[depth] = false;
    combi_with_recursion(count, depth+1, n, r);
  }


}

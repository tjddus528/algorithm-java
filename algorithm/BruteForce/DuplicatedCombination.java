package algorithm.BruteForce;

import java.util.Arrays;

public class DuplicatedCombination {
  static int[] arr;
  static int[] output;
  public static void main(String[] args) {
    int n = 3;
    int r = 2;
    arr = new int[]{1, 2, 3};
    output = new int[r];
    dupCombi(0, 0, n, r);

  }
  static void dupCombi(int start, int depth, int n, int r) {
    if (depth == r) {
      System.out.println(Arrays.toString(output));
      return;
    }
    for(int i=start; i<n; i++) {
      output[depth] = arr[i];
      dupCombi(i, depth+1, n, r);
    }
  }

}

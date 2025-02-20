package algorithm.BruteForce;

import java.util.Arrays;

public class DuplicatedPermutaion {
  static int[] arr;
  static int[] output;
  static int n, r;

  public static void main(String[] args) {
    n = 3;
    r = 2;
    arr = new int[]{1, 2, 3};
    output = new int[r];
    dupPermutation(0, n, r);
  }

  private static void dupPermutation(int depth, int n, int r) {
    if(depth==r) {
      System.out.println(Arrays.toString(output));
      return;
    }

    for(int i=0; i<n; i++) {
      output[depth] = arr[i];
      dupPermutation(depth+1, n, r);
    }
  }

}

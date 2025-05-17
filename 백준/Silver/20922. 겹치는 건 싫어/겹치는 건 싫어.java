import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
 
 
public class Main {
    static int n, k;
    static Map<String, Integer> map = new HashMap<>();
    static int[] arr;
    static int[] cnt;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String[] s = br.readLine().split(" ");
 
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
 
        String[] s1 = br.readLine().split(" ");
 
        arr = new int[n];
        cnt = new int[100001];
 
        for(int i=0; i<s1.length; i++){
            arr[i] = Integer.parseInt(s1[i]);
        }
        two();
 
        System.out.println(result);
    }
 
    private static void two() {
        int left = 0;
        int right = 0;
 
        while(left<=right){
            if(right<=n-1 && cnt[arr[right]] < k){
                cnt[arr[right]]++;
                right++;
            }
            else if(cnt[arr[right]]==k){
                cnt[arr[left]]--;
                left++;
            }
            result = Math.max(right-left,result);
 
            if(right==n){
                return;
            }
        }
    }
}
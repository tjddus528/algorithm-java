import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
 
 
public class Main {
 
 
    static int n,m;
    static int result;
    static int[][]map;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String[] s = br.readLine().split(" ");
 
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
 
        map = new int[n][m];
 
        check = new boolean[m];
        for(int i=0; i<n; i++){
            String[] s1 = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(s1[j]);
            }
        }
 
        dfs(0,0);
 
        System.out.println(result);
    }
    private static void dfs(int start, int depth){
        if(depth==3){
            int sum=0;
            for(int i=0; i<n; i++){
                int temp=0;
                for(int j=0; j<m; j++){
                    if(check[j]){
                        temp = Math.max(temp, map[i][j]);
                    }
                }
                sum+=temp;
            }
            result = Math.max(sum,result);
            return;
        }
 
        for(int i=start; i<m; i++){
            if(!check[i]){
                check[i]=true;
                dfs(i+1, depth+1);
                check[i]=false;
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static int N;
    static char[] anagram;
    static int[] check;
    static Set<String> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(N --> 0) {
            anagram = br.readLine().toCharArray();
            check = new int[26];
            for(char c: anagram) {
                check[c - 'a']++;
            }
            result = new TreeSet<>();
            perm(0, "");
            for(String s: result) sb.append(s).append("\n");
        }
        System.out.println(sb);
        
    }
    static void perm(int depth, String str) {
        if(depth == anagram.length) {
            result.add(str);
            return;
        }

        for (int i=0; i<26; i++) {
            if (check[i] == 0)
                continue;
            check[i]--;
            perm(depth + 1, str + (char)(i+'a'));
            check[i]++;
        }
    }
}

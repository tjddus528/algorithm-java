import java.util.*;
import java.io.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, minPrice = INF;
    static Book[] books;
    static int[] check = new int[26]; // 만들고자 하는 단어
    static int[] count = new int[26]; // 선택한 책에 포함된 단어
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        for (int i = 0; i < T.length(); i++) { // 찾으려는 단어에 포함된 문자의 수 세기
            check[T.charAt(i) - 'A']++;
        }

        N = Integer.parseInt(br.readLine());
        books = new Book[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int price = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            books[i] = new Book(price, name);
        }
        dfs(0, 0);
        if (minPrice == INF) System.out.println(-1);
        else System.out.println(minPrice);
    }

    static void dfs(int depth, int total) {
        if (depth == N) { // 모든 책을 탐색한 경우
            // 원하는 단어를 모두 찾았다면 더 작은 값으로 갱신하기
            if (checkWord()) minPrice = Math.min(minPrice, total);
            return;
        }

        Book book = books[depth];
        // 현재 책을 선택하는 경우
        for (int i = 0; i < book.name.length(); i++) {
            count[book.name.charAt(i) - 'A']++; // 책 제목에 포함된 단어 모두 세기
        }
        dfs(depth + 1, total + book.price);

        // 현재 책을 선택하지 않는 경우
        for (int i = 0; i < book.name.length(); i++) {
            count[book.name.charAt(i) - 'A']--; // 책 제목에 포함된 단어 셌던 거 모두 빼기
        }
        dfs(depth + 1, total);
    }

    static boolean checkWord() { // 원하는 단어를 만들 수 있는지 확인
        // 만들고 싶은 단어를 찾았다면 check 값은 count 값보다 작거나 같아야 함
        for (int i = 0; i < check.length; i++) {
            if (check[i] > count[i]) return false;
        }
        return true;
    }

    static class Book {
        int price;
        String name;

        public Book(int price, String name) {
            this.price = price;
            this.name = name;
        }
    }
}
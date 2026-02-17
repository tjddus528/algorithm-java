import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] sudoku = new int[9][9];
    static List<int[]> blank = new ArrayList<>();
    static int zeroCnt;
    static int cnt;
    static boolean endFlag;

    public static void main(String[] args) throws IOException {
        for(int i=0; i<9; i++) {
            String input = br.readLine();
            for(int j=0; j<9; j++) {
                sudoku[i][j] = input.charAt(j) - '0';
                if(sudoku[i][j] == 0) {
                    blank.add(new int[]{i, j});
                    zeroCnt++;
                }
            }
        }
        solve(0);
        print();
    }
    static void solve(int cur) {
        if(cur == zeroCnt) {
            endFlag = true;
            return;
        }

        for(int k=1; k<=9; k++) {
            sudoku[blank.get(cur)[0]][blank.get(cur)[1]] = k;
            if(check(blank.get(cur))) solve(cur+1);
            if(endFlag) return;
            sudoku[blank.get(cur)[0]][blank.get(cur)[1]] = 0;
        }
    }

    static boolean check(int[] blankPoint) {
        int x = blankPoint[0];
        int y = blankPoint[1];
        // horizon
        for(int i=0; i<9; i++) {
            if(i == x) continue;
            if(sudoku[i][y] == sudoku[x][y]) return false;
        }
        // vertical
        for(int i=0; i<9; i++) {
            if(i == y) continue;
            if(sudoku[x][i] == sudoku[x][y]) return false;
        }
        // box
        int tx = x/3 * 3;
        int ty = y/3 * 3;
        for(int i=tx; i<tx+3; i++){
            for(int j=ty; j<ty+3; j++) {
                if(i == x || j == y) continue;
                if(sudoku[i][j] == sudoku[x][y]) return false;
            }
        }
        return true;
    }
    static void print() throws IOException {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                bw.write(sudoku[i][j]+"");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}

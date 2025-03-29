import java.util.*;
class Solution {
    static int n;
    static List<List<Point>> board = new ArrayList<>();
    static List<List<Point>> puzzle = new ArrayList<>();
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        n = table.length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(table[i][j]==1) table[i][j] = 0;
                else table[i][j] = 1;
            }
        }
        
        boolean[][] visitedBoard = new boolean[n][n];
        boolean[][] visitedTable = new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(game_board[i][j]==0 && !visitedBoard[i][j])
                    bfs(i, j, visitedBoard, game_board, board);
                if(table[i][j]==0 && !visitedTable[i][j])
                    bfs(i, j, visitedTable, table, puzzle);
            }
        }
        
        boolean[] checked = new boolean[puzzle.size()];
        for(int i=0; i<board.size(); i++) {
            for(int j=0; j<puzzle.size(); j++) {
                if(checked[j] || board.get(i).size() != puzzle.get(j).size()) 
                    continue;
                if(check(board.get(i), puzzle.get(j))) {
                    checked[j] = true;
                    answer += board.get(i).size();
                    break;
                }
            }
        }
        
        return answer;
    }
    static void bfs(int x, int y, boolean[][] visited, int[][] board, List<List<Point>> list) {
        List<Point> subList = new ArrayList<>();
        subList.add(new Point(0, 0));
        
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.offer(new Point(x, y));
        visited[x][y] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
                if(board[nx][ny]==1) continue;
                if(visited[nx][ny]) continue;
                q.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                subList.add(new Point(nx-x, ny-y));
            }
        }
        list.add(subList);
        
    }
    
    static boolean check(List<Point> space, List<Point> block) {
        Collections.sort(space);
        for(int i=0; i<4; i++) {
            Collections.sort(block);
            int bx = block.get(0).x;
            int by = block.get(0).y;
            for(int j=0; j<block.size(); j++) {
                block.get(j).x -= bx;
                block.get(j).y -= by;
            }
            boolean isDiff = true;
            for(int j=0; j<space.size(); j++) {
                if(space.get(j).x!=block.get(j).x || space.get(j).y!=block.get(j).y)                 {
                    isDiff = false;
                    break;
                }
            }
            if(isDiff) return true;
            else {
                // 90도 회전 x,y => y, -x
                for(int j=0; j<block.size(); j++) {
                    int temp = block.get(j).x;
                    block.get(j).x = block.get(j).y;
                    block.get(j).y = -temp;
                }
            }
        }
        return false;
    }
    
    static class Point implements Comparable<Point> {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(x:"+x+", y:"+y+")";
        }
        public int compareTo(Point o) {
            int res = Integer.compare(this.x, o.x);
            if(res==0){
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }
    }
}
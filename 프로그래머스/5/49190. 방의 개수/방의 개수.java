import java.util.*;
class Solution {
    static final int MAX_SIZE = 100000;
    static int[] dx = {-1,-1,0,1,1, 1, 0,-1};
    static int[] dy = { 0, 1,1,1,0,-1,-1,-1};
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // 아래 두 함수가 없으면 객체의 주소를 비교하게 됨
        public int hashCode() {
            return Objects.hash(x,y);
        }
        public boolean equals(Object o) {
            return this.x == ((Point) o).x && this.y == ((Point) o).y;
        }
        // 디버깅을 위한
        @Override
        public String toString() {
            return "["+x+":"+y+"]";
        }
    } 
    public int solution(int[] arrows) {
        int answer = 0;
        Map<Point, List<Point>> map = new HashMap<>();
        // map.put(x+":"+y, true);
        map.put(new Point(0, 0), new ArrayList<>());
        int x = 0;
        int y = 0;
        for(int d: arrows) {
            int scale = 2;
            while(scale --> 0) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                Point now = new Point(x, y);
                Point next = new Point(nx, ny);
                // 재방문한 노드일 경우
                if(map.containsKey(next)) {
                    // 방문하지 않았던 경로일 경우
                    if(!map.get(next).contains(now)) {
                        // 새로운 연결 정보 추가
                        map.get(next).add(now);
                        map.get(now).add(next);
                        answer++;   // 방 추가
                    }
                }
                // 처음 방문한 노드일 경우
                else {
                    List<Point> list = new ArrayList<>();
                    map.put(next, list);
                    // 양쪽 연결 정보 추가
                    map.get(next).add(now);
                    map.get(now).add(next);
                }
                x = nx;
                y = ny;
                // System.out.println(map);
            }
        }
        return answer;
    }
}
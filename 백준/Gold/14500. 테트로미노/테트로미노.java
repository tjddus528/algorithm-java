import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] matrix;
    static int[][][] blocks = {
            {{1,1,1,1}},
            {{1,1},{1,1}},
            {{1,0},{1,0},{1,1}},
            {{0,1},{0,1},{1,1}},
            {{1,1},{1,0},{1,0}},
            {{1,0},{1,1},{0,1}},
            {{0,1},{1,1},{1,0}},
            {{1,1,1}, {0,1,0}},
            {{0,1,0}, {1,1,1}},
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = Integer.MIN_VALUE;
        for(int k=0; k<9; k++) {
            int[][] block = blocks[k];
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    // 정방향
                    int sum = 0;
                    for(int x=0; x<block.length; x++) {
                        for(int y=0; y<block[0].length; y++) {
                            if(i+x >= N) continue;
                            if(j+y >= M) continue;
                            if(block[x][y]==1) {
                                sum += matrix[i+x][j+y];
                            }
                        }
                    }
                    result = Math.max(result, sum);

                    // 시계 방향 90도 회전
                    sum = 0;
                    for(int x=0; x<block.length; x++) {
                        for(int y=0; y<block[0].length; y++) {
                            if(i+y >= N) continue;
                            if(j+(block.length-x-1) >= M) continue;
                            if(block[x][y]==1) {
                                sum += matrix[i+y][j+(block.length-x-1)];
                            }
                        }
                    }
                    result = Math.max(result, sum);

                    // 시계 방향 180도 회전
                    sum = 0;
                    for(int x=0; x<block.length; x++) {
                        for(int y=0; y<block[0].length; y++) {
                            if(i+(block.length-x-1) >= N) continue;
                            if(j+(block[0].length-y-1) >= M) continue;
                            if(block[x][y]==1) {
                                sum += matrix[i+(block.length-x-1)][j+(block[0].length-y-1)];
                            }
                        }
                    }
                    result = Math.max(result, sum);


                    // 시계 방향 270도 회전
                    sum = 0;
                    for(int x=0; x<block.length; x++) {
                        for(int y=0; y<block[0].length; y++) {
                            if(i+(block[0].length-y-1)>= N) continue;
                            if(j+x >= M) continue;
                            if(block[x][y]==1) {
                                sum += matrix[i+(block[0].length-y-1)][j+x];
                            }
                        }
                    }

                    result = Math.max(result, sum);
                }
            }
        }

        System.out.println(result);
    }

}

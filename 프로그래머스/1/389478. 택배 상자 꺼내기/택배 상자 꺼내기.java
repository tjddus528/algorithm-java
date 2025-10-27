import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int[][] box = new int[(n/w)+1][w];
        
        int k = 1;
        int i = 0;
        int j = 0;
        while(k <= n) {
            box[i][j] = k++;
            if(i%2==0) {
                if(j%w==w-1) i++;
                else j++;
            }
            else {
                if(j%w==0) i++;
                else j--;
            }
        }
        
        for(int x=0; x<(n/w)+1; x++) {
            for(int y=0; y<w; y++) {
                if(box[x][y]==num) {
                    for(int z=x; z<(n/w)+1; z++) {
                        if(box[z][y]!=0) answer++;
                    }
                }
            }
        }
        
        return answer;
    }
}
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int total = brown + yellow;     // 전체 격자의 개수
        
        // 3 <= 가로 길이
        
        for(int w=3; total/w >2; w++) {
            if(total%w==0 && w >= total/w && total/w > 2) {
                if(2*w+2*(total/w-2) == brown) {
                    answer = new int[]{w, total/w};
                    break;
                }
            }
        }
        return answer;
    }
}
import java.util.*;
class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> dp = new ArrayList<>();
        for(int i=0; i<=8; i++) dp.add(new ArrayList<>());
        for(int i=1; i<=8; i++) {
            Set<Integer> newSet = new HashSet<>();
            String str = "";
            for(int j=0; j<i; j++) str += String.valueOf(N);
            newSet.add(Integer.parseInt(str));
            
            for(int j=1; j<i; j++) {
                for(int num1: dp.get(j)) {
                    for(int num2: dp.get(Math.abs(i-j))) {
                        newSet.add(num1+num2);
                        newSet.add(num1-num2);
                        newSet.add(num1*num2);
                        if(num2>0) newSet.add((int)(num1/num2));
                    }
                }
            }

            for(int num: newSet) {
                if(num==number) return i;
                dp.get(i).add(num);
            }
        }
        return -1;
    }
}
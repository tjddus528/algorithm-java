import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        
        // 보석 종류의 개수 구하기
        Set<String> jewels = new HashSet<>();
        for(String gem: gems) jewels.add(gem);
        int N = jewels.size();
        
        // 전체 개수 만큼의 구간의 보석을 담기
        int left = 0;
        int right = 0;
        Map<String, Integer> bags = new HashMap<>();
        
        int minGap = Integer.MAX_VALUE;
        int a = 0;
        int b = N-1;
        
        while(left < gems.length && right < gems.length) {
            // 투포인터로 양쪽 포인터를 이동하면서 보석 개수 조정하기 -> 총 개수가 완성되면 break
            while(right < gems.length && bags.size() != N) {
                bags.put(gems[right], bags.getOrDefault(gems[right], 0)+1);
                right++;
            }
            while(left < gems.length && bags.size() == N) {
                if(bags.get(gems[left]) == 1) bags.remove(gems[left]);
                else bags.put(gems[left], bags.get(gems[left])-1);
                left++;
            }
            if(right - left < minGap) {
                a = left;
                b = right;
                minGap = right - left;
            }
        }
        
        return new int[]{a, b};
    }
}
import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // hahsmap -> 장르 : 총 재생 횟수
        Map<String, Integer> genre = new HashMap<>();
        int n = plays.length;
        for(int i=0; i<n; i++) {
            genre.put(genres[i], genre.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // HashMap -> List 변환 후 value값 기준으로 내림차순 정렬
        List<String> keySet = new ArrayList<>(genre.keySet());
        keySet.sort((o1, o2) -> genre.get(o2).compareTo(genre.get(o1)));
        
        // 장르 : 리스트<고유번호, 재생횟수> 저장
        Map<String, List<int[]>> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            if(!map.containsKey(genres[i])) {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{i, plays[i]});
                map.put(genres[i], list);
            } 
            else {
                map.get(genres[i]).add(new int[]{i, plays[i]});
            }
        }
        List<Integer> result = new ArrayList<>();
        for(String g: keySet) {
            map.get(g).sort((o1, o2) -> o2[1]-o1[1]);
            int cnt = 0;
            while(cnt < map.get(g).size() && cnt < 2)  {
                result.add(map.get(g).get(cnt)[0]);
                cnt++;
            }
        }
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) answer[i] = result.get(i);
        return answer;
    }
}
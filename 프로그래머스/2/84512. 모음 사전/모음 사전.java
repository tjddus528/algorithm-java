class Solution {
    static String alphabet = "AEIOU";
    static int order = 0;
    static int answer = 0;
    public int solution(String word) {
        dup_perm("", word);
        return answer;
    }
    static void dup_perm(String s, String word) {
        if(s.equals(word)) {
            answer = order;
            return;
        }
        if(s.length() >= 5) return;
        
        for(int i=0; i<5; i++) {
            String alpha = String.valueOf(alphabet.charAt(i));
            order++;
            dup_perm(s+alpha, word);
        }
    }
}
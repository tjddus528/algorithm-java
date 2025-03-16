import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] strList = new String[numbers.length];
        for(int i=0;i<numbers.length;i++) {
            strList[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(strList, (s1, s2) -> (s2+s2+s2).compareTo(s1+s1+s1));
        // System.out.println(Arrays.toString(strList));
        String str = String.join("", strList);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            if(sb.toString().isEmpty() && str.charAt(i)=='0') continue;
            else sb.append(str.charAt(i));
        }
        if(sb.toString().isEmpty()) return "0";
        return sb.toString();
    }
}
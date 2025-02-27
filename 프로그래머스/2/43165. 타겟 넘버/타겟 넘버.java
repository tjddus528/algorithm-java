class Solution {
    static int count;
    public int solution(int[] numbers, int target) {
        targetNumber(0, 0, numbers, target);
        return count;
    }
    static void targetNumber(int depth, int result, int[] numbers, int target) {
        if(depth==numbers.length) {
            if (result == target) count++;
            return;
        }
        targetNumber(depth+1, result+numbers[depth], numbers, target);
        targetNumber(depth+1, result-numbers[depth], numbers, target);
        
    }
}
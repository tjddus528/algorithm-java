import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> carFee = new HashMap<>();
        Map<Integer, Integer> carIn = new HashMap<>();
        Map<Integer, Integer> carParking = new HashMap<>();
        
        // records를 읽으면서 빈칸기준으로 split
        // 1) 시간 : 문자열->숫자로 변환
        // 2) 차량번호
        // 3) IN or OUT
        // 저장은 Map<차량번호, 출입시간>
        for(String record: records) {
            String[] data = record.split(" ");
            int time = strToTime(data[0]);
            int car = Integer.parseInt(data[1]);
            if(data[2].equals("IN")) carIn.put(car, time);
            else {
                int inTime = carIn.get(car);
                int gap = time - inTime;
                carIn.remove(car);
                carParking.put(car, carParking.getOrDefault(car, 0)+gap);   // 해당 차량의 누적 주차 시간
            }
        }
        Set<Integer> carNum = carIn.keySet();
        List<Integer> carNumList = new ArrayList<>(carNum);
        int idx = 0;
        while(!carIn.isEmpty()){
            int car = carNumList.get(idx++);
            int time = carIn.get(car);
            int gap = 1439-time;
            carIn.remove(car);
            carParking.put(car, carParking.getOrDefault(car, 0)+gap);   // 해당 차량의 누적 주차 시간
        }
        Set<Integer> allCar = carParking.keySet();
        List<Integer> carList = new ArrayList<>(allCar);
        Collections.sort(carList);
        int[] answer = new int[carList.size()];
        for(int i=0; i<carList.size(); i++) {
            int car = carList.get(i);
            int totalTime = carParking.get(car);
            int fee = calFee(totalTime, fees);
            answer[i] = fee;
        }
        return answer;
    }
    static int strToTime(String str) {
        String[] data = str.split(":");
        int hour = Integer.parseInt(data[0]);
        int min = Integer.parseInt(data[1]);
        return hour*60+min;
    }
    static int calFee(int time, int[] fees) {
        int price = fees[1];
        if(time<=fees[0]) return price;
        else {
            int gap = time-fees[0];
            price += Math.ceil((double)(time-fees[0])/fees[2]) * fees[3];
        }
        return price;
    }
}
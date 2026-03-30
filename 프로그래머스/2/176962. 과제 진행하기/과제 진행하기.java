import java.util.*;
class Solution {
    static class Process {
        String name;
        int end;
        Process(String name, int end) {
            this.name = name;
            this.end = end;
        }
        @Override
        public String toString() {
            return "{"+name+", "+end+"}";
        }
    }
    static class WaitProgram {
        String name;
        int remaining;
        WaitProgram(String name, int remaining) {
            this.name = name;
            this.remaining = remaining;
        }
        @Override
        public String toString() {
            return "{"+name+", "+remaining+"}";
        }
    }
    public String[] solution(String[][] plans) {
        int N = plans.length;
        int idx = 0;
        String[] answer = new String[N];
        // plans 시작 시간 순서로 정렬
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));
        
        ArrayDeque<String[]> planing = new ArrayDeque<>();
        ArrayDeque<Process> taskQ = new ArrayDeque<>();  // name, end time
        ArrayDeque<WaitProgram> waitQ = new ArrayDeque<>();  // name, remaining
        for(String[] plan: plans) planing.add(plan);     
        
        // 작업 시작 빠른 순서로 작업Q에 넣기
        while(!planing.isEmpty()) {
            String[] plan = planing.peek();

            // 다음 작업의 시작 시간과 비교 -> 이전 과제가 안 끝나면 남은 시간 계산해서 대기Q에 넣기
            // 동시에 대기Q에 중간에 멈춘 과제가 있는지
            if(taskQ.isEmpty()) {
                taskQ.add(new Process(plan[0],convertTime(plan[1])+Integer.parseInt(plan[2])));
                planing.poll();
                continue;
            }
            
            if(taskQ.peek().end < convertTime(plan[1])) {
                Process task = taskQ.poll();
                answer[idx++] = task.name;
                if(!waitQ.isEmpty()) {
                    WaitProgram waitTask = waitQ.pollLast();
                    taskQ.add(new Process(waitTask.name, task.end + waitTask.remaining));
                }
            }
            else if (taskQ.peek().end == convertTime(plan[1])) {
                Process task = taskQ.poll();
                answer[idx++] = task.name;
                taskQ.add(new Process(plan[0],convertTime(plan[1])+Integer.parseInt(plan[2])));
                planing.poll();
            }
            else if (!taskQ.isEmpty()) {
                Process task = taskQ.poll();
                int remaining = task.end - convertTime(plan[1]);
                waitQ.add(new WaitProgram(task.name, remaining));
                taskQ.add(new Process(plan[0],convertTime(plan[1])+Integer.parseInt(plan[2])));
                planing.poll();
            }
        }
        while(!taskQ.isEmpty()) {
            answer[idx++] = taskQ.poll().name;
        }
        while(!waitQ.isEmpty()) {
            answer[idx++] = waitQ.pollLast().name;
        }
        return answer;
    }
    private int convertTime(String str) {
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0])*60 + Integer.parseInt(strs[1]);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N, M, K, A, B;
    static int[] a;
    static int[] b;
    static int[] time;
    static class Customer {
        int num;
        int time;
        Customer(int _num, int _time) {
            this.num = _num;
            this.time = _time;
        }
        @Override
        public String toString() {
            return "["+num+", "+time+"]";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            a = new int[N+1];
            b = new int[M+1];
            time = new int[K+1];
            
            // 접수대기큐
            ArrayDeque<Customer> receptionQueue = new ArrayDeque<>();
            
            // 접수 창구
            int[] reception = new int[N+1];
            int[] receptionTime = new int[N+1];
            
            // 정비대기큐
            ArrayDeque<Customer> repairQueue = new ArrayDeque<>(); 
            
            // 정비 창구
            int[] repair = new int[M+1];
            int[] repairTime = new int[M+1];

            Set<Integer> wallet = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) a[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++) b[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                receptionQueue.add(new Customer(i, time[i]));
            }

            int result = 0;
            int cur = 0;
            while(repairQueue.size() < K) {
                // 현재 시간 cur에 대기큐에 있는 고객 중 접수시작이 가능하면 모두 시작하기
                // System.out.println(cur);
                for(int i=1; i<=N; i++) {
                    if(receptionTime[i] <= cur) {
                        // 접수 처리가 완료되면 정비 대기큐에 넣는다
                        if(reception[i]!=0) {
                            repairQueue.offer(new Customer(reception[i], receptionTime[i]));
                            reception[i] = 0;
                            receptionTime[i] = 0;
                        }
                        if(!receptionQueue.isEmpty() && receptionQueue.peek().time <= cur) {
                            Customer customer = receptionQueue.poll();
                            // 대기큐에 있던 가장 먼저 온 고객을 꺼내서, {고객번호랑 접수완료예정시각}을 넣기
                            reception[i] = customer.num;
                            receptionTime[i] = cur+a[i];
                            
                            if(i == A)
                                wallet.add(customer.num);
                        }
                    }
                }
                cur++;
            }

            cur = repairQueue.peek().time;
            while(!repairQueue.isEmpty()) {
                // 현재 시간 cur에 대기큐에 있는 고객 중 접수시작이 가능하면 모두 시작하기
                for(int i=1; i<=M; i++) {
                    if(repairTime[i] <= cur) {
                        if(!repairQueue.isEmpty() && repairQueue.peek().time <= cur) {
                            Customer customer = repairQueue.poll();
                            repair[i] = customer.num;
                            repairTime[i] = cur+b[i];

                            if(i == B) {
                                if(wallet.contains(customer.num)) result += customer.num;
                            }
                        }
                    }
                }
                cur++;
            }

            result = (result == 0) ? -1 : result;

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

}

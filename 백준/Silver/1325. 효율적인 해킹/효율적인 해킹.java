import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static ArrayList<Integer>[] list;
    static int[] count;
    static int n;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        count = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; ++i) list[i] = new ArrayList<>();

        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int computerA = Integer.parseInt(st.nextToken());
            int computerB = Integer.parseInt(st.nextToken());
            list[computerB].add(computerA); // b를 해킹하면 a도 해킹 가능
        }

        // n개의 컴퓨터를 BFS 탐색하여 해킹할 수 있는 컴퓨터의 수를 계산
        for (int i = 1; i < n + 1; ++i)
        {
            count[i] = bfs(i); // BFS를 통해 해당 노드에 연결된 컴퓨터의 개수를 저장
            if(count[i] > max) // 최댓값 갱신
            {
                max = count[i];
                answer.clear();
                answer.add(i);
            }
            else if(count[i] == max) answer.add(i); // 최댓값 추가
        }

        // 최댓값 출력
        for(int i : answer) sb.append(i).append(" ");
        System.out.println(sb);
    }

    // BFS 탐색
    static int bfs(int start)
    {
        boolean[] visited = new boolean[n + 1]; // 방문을 하였는지 저장하는 배열
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visited[start] = true;

        int cnt = 0; // 연결된 컴퓨터를 저장하는 변수

        while (!que.isEmpty())
        {
            int currentComputer = que.poll();

            for (int linkedComputer : list[currentComputer]) // 연결된(인접 리스트에 저장된) 컴퓨터를 순회
            {
                if (!visited[linkedComputer]) // 만약 방문하지 않았다면
                {
                    visited[linkedComputer] = true;
                    que.offer(linkedComputer);
                    ++cnt; // 연결된 컴퓨터 수 증가
                }
            }
        }

        return cnt;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N, K;
    static List<Integer> people = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        K = Integer.parseInt(data[1]);
        for(int i=1; i<=N; i++)
            people.add(i);
        List<Integer> order = new ArrayList<>();
        int index = K-1;
        while (!people.isEmpty()) {
            int get  = people.get(index);
            order.add(get);

            people.remove(index);
            if(people.isEmpty()) break;

            index = (index + K-1) % people.size();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i=0; i<order.size(); i++) {
            sb.append(order.get(i));
            if(i==order.size()-1) sb.append(">");
            else sb.append(", ");
        }
        System.out.println(sb);
    }
}

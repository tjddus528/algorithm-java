import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
public class Main {
	
	public static int T, N, M;
	public static int[] arr;
	public static int answer = 0;
	public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	T = Integer.parseInt(st.nextToken());
    	
    	for(int t=0;t<T;t++) {
            sb = new StringBuilder();
    		
	    	st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken());
	    	
	    	arr = new int[N];
	    	st = new StringTokenizer(br.readLine());
	    	for(int i=0;i<N;i++) {
	    		arr[i] =  Integer.parseInt(st.nextToken());
	    	}
	    	
	    	Arrays.sort(arr);
	    	
	    	
    		st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<M;j++) {
        		binarySearch(Integer.parseInt(st.nextToken()));
        	}
	        	
        	System.out.print(sb);
    	}
 
    	
    }
    
    public static void binarySearch(int target) {
    	int start = 0;
    	int end = N - 1;
    	
    	while(start <= end) {
    		int middle = (start + end) / 2;
    		
    		if(arr[middle] == target) {
    			sb.append('1').append("\n");
//    			System.out.println("1");
    			return ;
    		}
    		
    		if(arr[middle] < target) {
    			start = middle + 1;
    		}else {  // while(start <= end) 이기에 end를 더 작게만들어줘야함
    			end = middle - 1;
    		}
    		
    	}
    	sb.append('0').append("\n");
//		System.out.println("0");
    	
    	
    }
}
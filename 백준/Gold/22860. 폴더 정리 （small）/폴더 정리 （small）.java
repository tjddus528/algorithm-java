import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int diff;
	static int many;

	static class Folder{
		String name;
		ArrayList<Folder> child = new ArrayList<>();
		ArrayList<String> file = new ArrayList<>();
		Folder(){
			
		}
		Folder(String name){
			this.name = name;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] strs = br.readLine().split(" ");
		
		ArrayList<Folder> folder = new ArrayList<>();
		
		HashMap<String, Integer> indexFolder = new HashMap<>();  // Folder 이름 : 인덱스 
 		
		N = Integer.parseInt(strs[0]);
		M = Integer.parseInt(strs[1]);
		
		Folder root = new Folder("main");
		folder.add(root);
		indexFolder.put("main", 0);
		for(int i=0;i<N+M;i++) {
			strs = br.readLine().split(" ");
			String P = strs[0];
			String F = strs[1];
			int C = Integer.parseInt(strs[2]);
			if(C == 1) {
				Folder now = null;
				for(int j=0;j<folder.size();j++) {
					if(folder.get(j).name.equals(F)) {
						now = folder.get(j);
						break;
					}
				}
				if(now == null) {
					now = new Folder(F);
					folder.add(now);
					indexFolder.put(F, folder.size()-1); // 텅 빈 폴더를 만들고 
				}
				
				Folder parent = null;

				if(indexFolder.get(P)==null) {
					parent = new Folder(P);
					folder.add(parent);
					indexFolder.put(P, folder.size()-1); // 텅 빈 폴더를 만들고 
				}
				else {
					int index = indexFolder.get(P);
					parent = folder.get(index);
				}

				parent.child.add(now);  //이름으로 -> 인덱스를 찾아서 -> 부모 폴더를 찾아서 -> child에 추가 
				
			}
			else if(C == 0) {
				Folder parent = null;

				if(indexFolder.get(P)==null) {
					parent = new Folder(P);
					folder.add(parent);
					indexFolder.put(P, folder.size()-1); // 텅 빈 폴더를 만들고 
				}
				else {
					int index = indexFolder.get(P);
					parent = folder.get(index);
				}
				parent.file.add(F); // 이름으로 -> 인덱스를 찾아서 -> 부모 폴더를 찾아서 ->file에 추가 
				
			}
		}

		
		int Q = Integer.parseInt(br.readLine());
		for(int i=0;i<Q;i++) {
			many =0;
			HashSet<String> diff = new HashSet<>();
			strs = br.readLine().split("/");
			int index = indexFolder.getOrDefault(strs[strs.length-1], -1);
			if (index == -1) {
			    continue;
			} else {
			    Folder find = folder.get(index);
			    findFolder(find, diff);
			    System.out.println(diff.size() + " " + many);
			}

		}
		
	}
	public static void findFolder(Folder find, HashSet<String> diff) {
		many +=find.file.size();
		for(String s : find.file) {
			diff.add(s);
		}
		if(find.child.size()!=0) {
			for(Folder f : find.child)
				findFolder(f,diff);
		}
	}
}
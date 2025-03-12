import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 4195번 친구 네트워크
 * - Union Find
 */

public class Main {
	public static int[] parent, size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			Map<String, Integer> map = new HashMap<>();
			
			int F = Integer.parseInt(br.readLine());
			
			parent = new int[F * 2];
			size = new int[F * 2];
			
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
				size[i] = 1;
			}
			
			int index = 0;
			
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				
				if (!map.containsKey(f1)) {
					map.put(f1, index++);
				}
				
				if (!map.containsKey(f2)) {
					map.put(f2, index++);
				}
				
				int i1 = map.get(f1);
				int i2 = map.get(f2);
				
				sb.append(union(i1, i2) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static int find(int f) {
		if (parent[f] == f) return f;
		return parent[f] = find(parent[f]);
	}
	
	public static int union(int f1, int f2) {
		int root1 = find(f1);
		int root2 = find(f2);
		
		if (root1 != root2) {
			parent[root2] = root1;
			size[root1] += size[root2];
			return size[root1];
		}
		
		return size[root1];
	}
}

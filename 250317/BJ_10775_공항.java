import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 10775번 공항
 * - Union-Find 사용
 */

public class Main {
	public static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parent = new int[G + 1];
		for (int i = 0; i <= G; i++) {
			parent[i] = i;
		}
		
		int count = 0;
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			
			if (find(g) == 0) break;
			
			count++;
			union(find(g) - 1, find(g));
		}
		
		System.out.println(count);
	}
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static void union(int prev, int next) {
		prev = find(prev);
		next = find(next);
		
		if (prev != next) {
			parent[next] = find(prev);
		}
	}
}

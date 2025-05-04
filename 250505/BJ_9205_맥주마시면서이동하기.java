import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 9205번 맥주 마시면서 걸어가기 
 * - BFS
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for (int T = 0; T < t; T++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] home = new int[2];
			st = new StringTokenizer(br.readLine());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			List<int[]> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				list.add(new int[] { x, y });
			}
			
			int[] festival = new int[2];
			st = new StringTokenizer(br.readLine());
			festival[0] = Integer.parseInt(st.nextToken());
			festival[1] = Integer.parseInt(st.nextToken());
			
			boolean isPossible = false;
			boolean[] visited = new boolean[list.size()];
			
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(home);
			
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				int dist = Math.abs(cur[0] - festival[0]) + Math.abs(cur[1] - festival[1]);
				if (dist <= 1000) {
					isPossible = true;
					break;
				}
				
				for (int i = 0; i < list.size(); i++) {
					int[] next = list.get(i);
					
					dist = Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]);
					if (dist <= 1000 && !visited[i]) {
						queue.offer(next);
						visited[i] = true;
					}
				}
			}
			
			System.out.println(isPossible ? "happy" : "sad");
		}
	}
}

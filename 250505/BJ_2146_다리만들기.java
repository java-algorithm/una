import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2146번 다리 만들기
 * - BFS로 각 섬 번호 붙인 뒤 가장 짧은 다리 탐색
 */

public class Main {
	public static int N, map[][];
	public static boolean[][] visited;
	public static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		
		int label = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					labelIsland(i, j, label++);
				}
			}
		}
		
		System.out.println(buildBridge(label));
	}
	
	public static void labelIsland(int x, int y, int label) {
		Queue<int []> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		map[x][y] = label;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int[] d : deltas) {
				int dx = cur[0] + d[0];
				int dy = cur[1] + d[1];
				
				if (dx < 0 || dy < 0 || dx >= N || dy >= N || visited[dx][dy] || map[dx][dy] == 0) continue;
				
				queue.offer(new int[] {dx, dy});
				visited[dx][dy] = true;
				map[dx][dy] = label;
			}	
		}
	}
	
	public static int buildBridge(int islandCount) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][N][islandCount];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 1) {
					queue.offer(new int[] {i, j, 0, map[i][j]});
					visited[i][j][map[i][j]] = true;
				}
				
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int[] d : deltas) {
				int dx = cur[0] + d[0];
				int dy = cur[1] + d[1];
				
				if (dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
				
				// 바다면 계속 이동
				if (map[dx][dy] == 0 && !visited[dx][dy][cur[3]]) {
					queue.offer(new int[] {dx, dy, cur[2] + 1, cur[3]});
					visited[dx][dy][cur[3]] = true;
				} else if (map[dx][dy] != 0 && map[dx][dy] != cur[3]) {
					min = Math.min(min, cur[2]);
				}
			}
		}
		
		return min;
	}
}

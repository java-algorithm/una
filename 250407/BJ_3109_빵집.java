import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3109번 빵집
 * - DFS
 */
 
public class Main {
	public static int R, C, result = 0;
	public static int[][] deltas = {{-1, 1}, {0, 1}, {1, 1}};
	public static char[][] map;
	public static boolean[][] visited;
	public static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j); 
			}
		}
		
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0);
		}
		
		System.out.println(result);
	}
	
	public static void dfs(int x, int y) {
		if (y == C - 1) {
			result++;
			flag = true;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int dx = x + deltas[i][0];
			int dy = y + deltas[i][1];
			
			if (dx < 0 || dy < 0 || dx >= R || dy >= C || visited[dx][dy] || map[dx][dy] == 'x') continue;
			
			if (flag) return;
			else visited[dx][dy] = true;
			
			dfs(dx, dy);
		}
	}
}

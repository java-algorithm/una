import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1600번 말이 되고픈 원숭이
 * - BFS?
 */

public class Main {
	public static int[][] horse = {{-2, -1}, {2, 1}, {-1, -2}, {1, 2}, {-2, 1}, {2, -1}, {1, -2}, {-1, 2}};
	public static int[][] kiki = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = -1;
		
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[H][W][K + 1];
		
		// {x 좌표, y 좌표, 말 이동 횟수, 이동 횟수}
		queue.offer(new int[] {0, 0, 0, 0});
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int x = cur[0];
			int y = cur[1];
			int horseCnt = cur[2];
			int dist = cur[3];
						
			if (x == H - 1 && y == W - 1) {
				answer = dist;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int dx = x + kiki[i][0];
				int dy = y + kiki[i][1];
				
				if (dx < 0 || dy < 0 || dx >= H || dy >= W || map[dx][dy] == 1) continue;
				
				if (!visited[dx][dy][horseCnt]) {
					queue.offer(new int[] {dx, dy, horseCnt, dist + 1});
					visited[dx][dy][horseCnt] = true;
				}
			}
			
			if (horseCnt < K) {
				for (int i = 0; i < 8; i++) {
					int dx = x + horse[i][0];
					int dy = y + horse[i][1];
					
					if (dx < 0 || dy < 0 || dx >= H || dy >= W || map[dx][dy] == 1) continue;
					
					if (!visited[dx][dy][horseCnt + 1]) {
						queue.offer(new int[] {dx, dy, horseCnt + 1, dist + 1});
						visited[dx][dy][horseCnt + 1] = true;
					}
				}
			}
		}
		
		System.out.println(answer);
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int answer = -1;
		
		Queue<int []> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];
		
		// {x, y, 벽을 부순 횟수, 이동 거리}
		queue.offer(new int[] {0, 0, 0, 1});
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if (cur[0] == N - 1 && cur[1] == M - 1) {
				answer = cur[3];
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int dx = cur[0] + deltas[i][0];
				int dy = cur[1] + deltas[i][1];
				
				if (dx < 0 || dy < 0 || dx >= N || dy >= M) continue;
				
				if (map[dx][dy] == 0 && !visited[dx][dy][cur[2]]) {
					visited[dx][dy][cur[2]] = true;
					queue.offer(new int[] {dx, dy, cur[2], cur[3] + 1});
				}
				
				if (map[dx][dy] == 1 && cur[2] == 0 && !visited[dx][dy][1]) {
					visited[dx][dy][cur[2]] = true;
					queue.offer(new int[] {dx, dy, 1, cur[3] + 1});
				}
			}			
		}
		
		System.out.println(answer);
	}
}

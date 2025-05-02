import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 3055번 탈출
 * - BFS
 */

public class Main {
	public static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		
		Queue<int []> water = new ArrayDeque<>();
		Queue<int []> hedgehog = new ArrayDeque<>();
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'S') {
					hedgehog.offer(new int[] {i, j, 0});
				} else if (map[i][j] == '*') {
					water.offer(new int[] {i, j});
				}
			}
		}
		
		while (!hedgehog.isEmpty()) {
			int[] hCur = hedgehog.peek();
			
			int size = water.size();
			
			for (int i = 0; i < size; i++) {
				int[] wCur = water.poll();
				
				for (int j = 0; j < 4; j++) {
					int wx = wCur[0] + deltas[j][0];
					int wy = wCur[1] + deltas[j][1];
					
					if (wx >= R || wy >= C || wx < 0 || wy < 0 || map[wx][wy] == 'D' || map[wx][wy] == 'X') continue;
					
					if (map[wx][wy] == '.') {
						water.offer(new int[] {wx, wy});
						map[wx][wy] = '*';
					}
				}
			}
			
			size = hedgehog.size();
			
			for (int i = 0; i < size; i++) {
				hCur = hedgehog.poll();
				
				for (int j = 0; j < 4; j++) {
					int dx = hCur[0] + deltas[j][0];
					int dy = hCur[1] + deltas[j][1];
					
					if (dx >= R || dy >= C || dx < 0 || dy < 0 || map[dx][dy] == '*' || map[dx][dy] == 'X') continue;
					
					if (map[dx][dy] == 'D') {
						System.out.println(hCur[2] + 1);
						return;
					} else if (map[dx][dy] == '.') {
						map[dx][dy] = 'S';
						hedgehog.offer(new int[] {dx, dy, hCur[2] + 1});
					}
					
				}
			}
		}
		
		System.out.println("KAKTUS");
	}
}

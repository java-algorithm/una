package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2573번 빙산
 * - 두 덩어리 이상으로 분리되는지 확인 : Bfs 탐색
 * - 아직 녹지 않은 빙산 List에 넣어서 관리
 */

public class BJ_2573_빙산 {
	public static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	public static int N, M, iceberg[][];
	public static List<int[]> icebergs;
	public static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 3 <= N <= 300
		M = Integer.parseInt(st.nextToken()); // 3 <= M <= 300
		
		iceberg = new int[N][M]; // 빙산 높이 저장할 배열
		icebergs = new ArrayList<>(); // 높이가 0이 아닌 빙산 저장할 리스트
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				iceberg[i][j] = Integer.parseInt(st.nextToken());

				if (iceberg[i][j] > 0) icebergs.add(new int[] {i, j});
			}
		}
		
		int year = 0;
		
		while (true) {
			visited = new boolean[N][M];
			
			if (isSeperate()) {
				System.out.println(year);
				break;
			}
			
			if (icebergs.isEmpty()) {
				System.out.println(0);
				break;
			}
			
			
			meltIceberg();
			year++;			
		}
	}
	
	// 빙산 녹이기
	
	public static void meltIceberg() {
		List<int []> newIcebergs = new ArrayList<>();
		int[][] newIceberg = new int[N][M];
		
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				newIceberg[i][j] = iceberg[i][j];
			}
		}
		
		for (int[] ib : icebergs) {
			int x = ib[0];
			int y = ib[1];
			
			int waterCount = 0;

            for (int i = 0; i < 4; i++) {
                int dx = x + deltas[i][0];
                int dy = y + deltas[i][1];

                if (dx >= 0 && dy >= 0 && dx < N && dy < M && iceberg[dx][dy] == 0) waterCount++;
            }
            
            if (newIceberg[x][y] > waterCount) {
            	newIceberg[x][y] -= waterCount;
                newIcebergs.add(new int[]{x, y});
            } else newIceberg[x][y] = 0;
		}

		// 배열, 리스트 복사하기
        icebergs = newIcebergs;
        iceberg = newIceberg;
	}
	
	// 두 덩어리 이상으로 분리되는지 확인하는 메소드
	
	public static boolean isSeperate() {
		int count = 0;
		
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (iceberg[i][j] != 0 && !visited[i][j]) {
					bfs(i, j);
					count++;
				}
				if (count >= 2) return true;
			}
		}
		
		return false;
	}
	
	// 연결된 덩어리 탐색 메소드
	
	public static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int dx = cur[0] + deltas[i][0];
				int dy = cur[1] + deltas[i][1];
				
				if (dx >= 1 && dy >= 1 && dx < N - 1 && dy < M - 1 && iceberg[dx][dy] != 0 && !visited[dx][dy]) {
					visited[dx][dy] = true;
					queue.add(new int[] {dx, dy});
				}
			}
		}
	}
}

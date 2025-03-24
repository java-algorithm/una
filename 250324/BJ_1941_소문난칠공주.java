import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 백준 1941번 소문난 칠공주
 * 조합 + BFS 탐색
 */

public class Main {
	public static String[][] seats;
	public static int answer = 0;
	public static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static List<Integer> princess;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		seats = new String[5][5];
		princess = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			seats[i] = br.readLine().split("");
		}
		
		comb(0, 0);
		
		System.out.println(answer);
	}
	
	public static void comb(int count, int start) {
		if (count == 7) {
			if (isValid(princess)) answer++;
			return;
		}
		
		// 5x5 2차원 배열을 0부터 24로 설정
		for (int i = start; i < 25; i++) {
			princess.add(i);
			comb(count + 1, i + 1);
			princess.remove(princess.size() - 1);
		}
	}
	
	public static boolean isValid(List<Integer> princess) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[5][5];
		
		int startX = princess.get(0) / 5;
		int startY = princess.get(0) % 5;
		
		queue.add(new int[] {startX, startY});
		isVisited[startX][startY] = true;
		
		int pCount = 1;
		int sCount = (seats[startX][startY].equals("S") ? 1 : 0);
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int dx = x + deltas[i][0];
				int dy = y + deltas[i][1];
				
				if (dx < 0 || dy < 0 || dx >= 5 || dy >= 5 || isVisited[dx][dy]) continue;
				
				if (princess.contains(dx * 5 + dy)) {
					queue.add(new int[] {dx, dy});
					isVisited[dx][dy] = true;
					pCount++;
					
					if (seats[dx][dy].equals("S")) sCount++;
				}
			}
		}
		
		return pCount == 7 && sCount >= 4;
	}
}

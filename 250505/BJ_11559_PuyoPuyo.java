import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static char[][] map;
	public static int[][] deltas = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int answer = 0;
		
		while (true) {
            boolean isPopped = false;
            boolean[][] visited = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        if (bfs(i, j, visited)) isPopped = true;
                    }
                }
            }

            if (!isPopped) break; // 더 이상 터질 뿌요가 없으면 종료

            fall(); // 뿌요 내리기
            answer++; // 연쇄 증가
        }
		
		System.out.println(answer);
	}
	
	public static boolean bfs(int x, int y, boolean[][] visited) {
		List<int[]> list = new ArrayList<>();
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		list.add(new int[] {x, y});
		
		char color = map[x][y];
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int[] d : deltas) {
				int dx = cur[0] + d[0];
				int dy = cur[1] + d[1];
				
				if (dx < 0 || dy < 0 || dx >= 12 || dy >= 6 || visited[dx][dy]) continue;
				
				if (map[dx][dy] == color) {
					queue.offer(new int[] {dx, dy});
					visited[dx][dy] = true;
					list.add(new int[] {dx, dy});
				}
			}
		}
		
		if (list.size() >= 4) {
			// 해당 색깔 뿌요 지우기
			for (int[] l : list) map[l[0]][l[1]] = '.';
			
			return true;
		}
		
		return false;
	}
	
	public static void fall() {
	    for (int j = 0; j < 6; j++) {
	        int emptyRow = 11; // 가장 아래부터 시작

	        for (int i = 11; i >= 0; i--) {
	            if (map[i][j] != '.') {
	                char temp = map[i][j];
	                
	                map[i][j] = '.';
	                map[emptyRow][j] = temp;
	                emptyRow--;
	            }
	        }
	    }
	}
}

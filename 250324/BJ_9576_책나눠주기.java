import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 9576번 책 나눠주기 
 * 이분 매칭 사용
 */

public class Main {
	public static List<Integer>[] student;
	public static boolean[] done; // DFS에서 i번 책을 고려한 적이 있는지
	public static int[] books; // i번째 책이 누구에게 나눠졌는지
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			student = new ArrayList[M];
			books = new int[N + 1];
			Arrays.fill(books, -1);
			
			for (int i = 0; i < M; i++) {
				student[i] = new ArrayList<>();
				
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				for (int j = a; j <= b; j++) {
					student[i].add(j);
				}
			}
			
			for (int i = 0; i < M; i++) {
				done = new boolean[N + 1];
				if (dfs(i)) answer += 1;
			}
			
			System.out.println(answer);
		}
	}
	
	public static boolean dfs(int x) {
		for (int book : student[x]) {
			if (done[book]) continue;
			done[book] = true;
				
			if (books[book] == -1 || dfs(books[book])) {
				books[book] = x;
				return true;
			}
		}
		return false;
	}
}

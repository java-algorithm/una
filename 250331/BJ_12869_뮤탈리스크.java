	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.StringTokenizer;
	
	public class Main {
		public static int min = Integer.MAX_VALUE;
		public static boolean[][][] visited;
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] scv = new int[3];
			visited = new boolean[61][61][61];
			
			for (int i = 0; i < N; i++) {
				scv[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(scv[0], scv[1], scv[2], 0);
			
			System.out.println(min);
		}
		
		public static void dfs(int s1, int s2, int s3, int count) {
			s1 = Math.max(0, s1);
			s2 = Math.max(0, s2);
			s3 = Math.max(0, s3);
			
			if (s1 == 0 && s2 == 0 && s3 == 0) {
				min = Math.min(min, count);
				return;
			}
			
			Integer[] s = {s1, s2, s3};
			Arrays.sort(s, Collections.reverseOrder());
			
			if (visited[s[0]][s[1]][s[2]]) return;
			else visited[s[0]][s[1]][s[2]] = true;
			
			if (count > min) return;
			
			dfs(s[0] - 9, s[1] - 3, s[2] - 1, count + 1);
			dfs(s[0] - 9, s[1] - 1, s[2] - 3, count + 1);
			dfs(s[0] - 3, s[1] - 9, s[2] - 1, count + 1);
			dfs(s[0] - 3, s[1] - 1, s[2] - 9, count + 1);
			dfs(s[0] - 1, s[1] - 9, s[2] - 3, count + 1);
			dfs(s[0] - 1, s[1] - 3, s[2] - 9, count + 1);
			
			return;
		}
	}
	

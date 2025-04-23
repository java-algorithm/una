import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static boolean[][] dp;
	public static boolean[] possible;
	public static int[] weights;
	public static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		weights = new int[N];
		dp = new boolean[N + 1][15001];
		possible = new boolean[15001];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int marble = Integer.parseInt(st.nextToken());
			if (marble > 15000) sb.append("N ");
			else sb.append(possible[marble] ? "Y " : "N ");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int idx, int w) {
		if (idx > N || dp[idx][w]) return;
		
		dp[idx][w] = true;
		possible[w] = true;
		
		if (idx == N) return;
		
		dfs(idx + 1, w + weights[idx]);
		
		dfs(idx + 1, Math.abs(w - weights[idx]));
		
		dfs(idx + 1, w);
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12865번 평범한 배낭
 * - dp 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int object[][] = new int[N + 1][2];
		int dp[][] = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			object[i][0] = Integer.parseInt(st.nextToken());
			object[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j >= object[i][0]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - object[i][0]] + object[i][1]);
				else dp[i][j] = dp[i - 1][j];
			}
		}
		
		System.out.println(dp[N][K]);
	}
}

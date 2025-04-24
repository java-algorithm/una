import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1563번 개근상
 * - dp 사용
 */
 
public class Main {
	static final int MOD = 1000000;  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][][] dp = new int[N][2][3];
		dp[0][0][0] = 1; // 출석
		dp[0][1][0] = 1; // 지각
		dp[0][0][1] = 1; // 결석
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 3; k++) {
					int value = dp[i - 1][j][k];
					
					// 출석
					dp[i][j][0] = (dp[i][j][0] + value) % MOD;
					
					// 지각
					if (j == 0) dp[i][1][0] = (dp[i][1][0] + value) % MOD;
					
					// 결석
					if (k < 2) dp[i][j][k + 1] = (dp[i][j][k + 1] + value) % MOD;
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				answer = (answer + dp[N - 1][i][j]) % MOD;
			}
		}
		
		System.out.println(answer);
	}
}

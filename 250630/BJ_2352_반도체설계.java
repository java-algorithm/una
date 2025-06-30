import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2352번 반도체 설계
 * - DP
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		dp[1] = 1;
		int lis = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] port = new int[n + 1];
		for (int i = 1; i <= n; i++) port[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 2; i <= n; i++) {
			dp[i] = 1;
			
			for (int j = 1; j < i; j++) {
				// 겹치는지 확인
				if (port[i] > port[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					lis = Math.max(lis, dp[i]);
				}
			}
		}
		System.out.println(lis);
	}
}

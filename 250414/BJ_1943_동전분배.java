import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1943번 동전 분배
 * - 배낭
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 0; t < 3; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int sum = 0;
			
			int[][] coins = new int[N][2];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int amount = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				
				coins[i][0] = amount;
				coins[i][1] = count;
				
				sum += amount * count;
			}
			
			if (sum % 2 == 1) {
				System.out.println(0);
				continue;
			}
			
			int mid = sum / 2;
			
			boolean[] dp = new boolean[mid + 1];
			boolean[] tmp = new boolean[mid + 1];
			dp[0] = true;
			
			for (int i = 0; i < N; i++) {
				int amount = coins[i][0];
				int count = coins[i][1];
				
				tmp = dp.clone();
				
				for (int j = 1; j <= count; j++) {
					int cost = amount * j;
					for (int k = 0; k + cost <= mid; k++) {
						if (dp[k]) tmp[k + cost] = true;
					}
				}
				
				dp = tmp;
			}
			
			System.out.println(dp[mid] ? 1 : 0);
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1285번 동전 뒤집기
 * 1. N개의 각 행을 뒤집을지 안 뒤집을지 결정
 * 2. 각 경우에 따라 각 열이 뒷면이 더 많을 경우 뒤집기
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] coins = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			
			for (int j = 0; j < N; j++) {
				if (str[j].equals("H")) coins[i][j] = 1;
				else coins[i][j] = 0;
			} 
		}
		
		int answer = Integer.MAX_VALUE;
		
		// 1 << N = Math.pow(2, N) = 2의 N제곱
		for (int bit = 0; bit < (1 << N); bit++) {
			int sum = 0;
			
			for (int col = 0; col < N; col++) {
				int back = 0; // T인 동전 개수
				
				for (int row = 0; row < N; row++) {
					int cur = coins[row][col];
					
					/*
					 * bit = 011(=3)일 경우 (0번째, 1번째 행만 뒤집음)
					 * 
					 * bit & (1 << 0) != 0이므로 0번째 행 뒤집음
					 * bit & (1 << 1) != 0이므로 1번째 행 뒤집음
					 * bit & (1 << 2) == 0이므로 2번째 행 뒤집지 않음
					 */
					
					if ((bit & (1 << row)) != 0) cur ^= 1;
					
					if (cur == 0) back++; // T인 동전 개수 세기
					
				}
				
				// 각 열에서 T의 개수와 H의 개수 중 적은 쪽을 뒤집음
				sum += Math.min(back, N - back);
			}
			answer = Math.min(answer, sum);
			
		}
		
		System.out.println(answer);
	}
}

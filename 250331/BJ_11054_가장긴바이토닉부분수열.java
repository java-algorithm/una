import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11054번 가장 긴 바이토닉 부분 수열
 * - 양방향에서 증가하는 수열의 최대 길이를 세는 dp 배열 2개 사용
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp1 = new int[N]; // 왼 -> 오
		int[] dp2 = new int[N]; // 오 -> 왼
				
		dp1[0] = 1;
		for (int i = 1; i < N; i++) {
			dp1[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp1[i] = Math.max(dp1[i], dp1[j] + 1);
				}
			}
		}
		
		dp2[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			dp2[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, dp1[i] + dp2[i] - 1);
		}
		
		System.out.println(answer);
	}
	
}

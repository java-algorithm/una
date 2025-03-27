import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp1 = new int[N]; // 왼쪽에서 오른쪽 방향으로
		int[] dp2 = new int[N]; // 오른쪽에서 왼쪽 방향으로
		
		dp1[0] = nums[0];
		int answer = dp1[0];
		
		// 왼 -> 오 누적합
		for (int i = 1; i < N; i++) {
			dp1[i] = Math.max(nums[i] + dp1[i - 1], nums[i]);
			answer = Math.max(answer, dp1[i]);
		}
		
		// 오 -> 왼 누적합
		dp2[N - 1] = nums[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			dp2[i] = Math.max(nums[i] + dp2[i + 1], nums[i]);
			answer = Math.max(answer, dp2[i]);
		}
		
		// i번째를 제외한 합
		for (int i = 1; i < N - 1; i++) {
			answer = Math.max(answer, dp1[i - 1] + dp2[i + 1]);
		}
		
		System.out.println(answer);
	}
}

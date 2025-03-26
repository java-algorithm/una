import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 14002번 가장 긴 증가하는 부분 수열 4 
 * - LIS + dp
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		dp[0] = 1;
		int lis = 1;
		
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					lis = Math.max(lis, dp[i]);
				}
			}
		}
		
		sb.append(lis + "\n");
		
		Stack<Integer> stack = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			if (dp[i] == lis) {
				stack.push(nums[i]);
				lis--;
			}
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		
		System.out.println(sb.toString());
	}
}

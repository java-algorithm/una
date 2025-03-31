import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1959번 달팽이3
 * 꺾어지는 횟수와 끝나는 좌표 나눠서 구하기
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long M = Integer.parseInt(st.nextToken());
		long N = Integer.parseInt(st.nextToken());
		
		long count = 0;
		long x = 0;
		long y = 0;
		
		/**
		 * M = 5, N = 3일 경우 5
		 * M = 3, N = 5일 경우 4
		 * M = 4, N = 4일 경우 10
		 */
		
		if (M > N) {
			count = (N - 1) * 2 + 1;
		} else {
			count = (M - 1) * 2;
		}
		
		if (M == N) {
			if (M % 2 == 0) { // 짝수일 경우
				x = M / 2 + 1;
				y = N / 2;
			} else { // 홀수일 경우
				x = M / 2 + 1;
				y = N / 2 + 1;
			}
		} else if (M > N) {
			if (N % 2 == 0) {
				x = N / 2 + 1;
				y = N / 2;
			} else {
				x = N / 2 + 1 + (M - N);
				y = N / 2 + 1;
			}
		} else {
			if (M % 2 == 0) {
				x = M / 2 + 1;
				y = M / 2;
			} else {
				x = M / 2 + 1;
				y = M / 2 + 1 + (N - M);
			}
		}
		
		System.out.println(count + "\n" + x + " " + y);
	}
}

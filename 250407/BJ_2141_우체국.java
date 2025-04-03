import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2141번 우체국
 * - 그리디
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		long[][] viliage = new long[N][2]; // <=1,000,000,000
		long people = 0; // <=1,000,000,000
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			viliage[i][0] = Integer.parseInt(st.nextToken());
			viliage[i][1] = Integer.parseInt(st.nextToken());
			
			people += viliage[i][1];
		}
		
		// 최악의 경우 O(N^2)지만 거의 발생할 확률이 높지 않음?
		Arrays.sort(viliage, (o1, o2) -> Long.compare(o1[0], o2[0]));
		
		long answer = 0;
		long sum = 0;
		long mid = (people + 1) / 2; // 중간 인원의 값
		
		// O(N)
		for (int i = 0; i < N; i++) {
			sum += viliage[i][1];
			
			if (sum >= mid) {
				answer = viliage[i][0];
				break;
			}
			
		}
		
		System.out.println(answer);
	}
}

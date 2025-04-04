import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2437번 저울
 * - 그리디 + 정렬
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] weight = new int[N];
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(weight); // 오름차순 정렬
		
		// 앞에서부터 합을 구한 뒤 sum + 1이 다음 추보다 작을 경우 해당 무게를 측정할 수 없음 : break
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (sum + 1 >= weight[i]) {
				sum += weight[i];				
			} else break;
		}
		
		System.out.println(sum + 1);
	}
}

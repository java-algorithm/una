import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String[] nums = new String[N];
		for (int i = 0; i < N; i++) nums[i] = st.nextToken();
		
		Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));

		for (int i = 0; i < N; i++) sb.append(nums[i] + "");
		
		if (sb.toString().charAt(0) == '0') System.out.println(0);
		else System.out.println(sb.toString());
	}
}

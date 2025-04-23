import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int diff = y - x;
			
			int n = 0;
			
			while (true) {				
				if (diff - (n + 2) < 0) break; 
				else {
					n += 2;
					diff -= n;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			if (diff == 0) sb.append(n);
			else if (diff > 0 && diff <= (n + 2) / 2) sb.append(n + 1);
			else sb.append(n + 2);
			
			System.out.println(sb);
		}
	}
}

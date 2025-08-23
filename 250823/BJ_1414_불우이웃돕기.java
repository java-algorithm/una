import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 백준 1414번 불우이웃돕기
 * - 최소 스패닝 트리 사용
 * - 자기 자신을 연결하는 랜선은 필요 없음
 * - 컴퓨터가 모두 연결되지 않는다면 -1 리턴
 * 
 * 틀린 이유
 * 1. 랜선이 1개인 경우 -1이 아닌 값 리턴해야 함
 * 2. 컴퓨터가 모두 연결되어 있는지 검증해야 함
 */

public class Main {
	static int[] parent;

	public static class LANCable implements Comparable<LANCable> {
		int to, from, len;

		LANCable(int to, int from, int len) {
			this.to = to;
			this.from = from;
			this.len = len;
		}

		@Override
		public int compareTo(LANCable o) {
			return Integer.compare(this.len, o.len);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) parent[i] = i;

		PriorityQueue<LANCable> pq = new PriorityQueue<>();

		int sum = 0;

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();

			for (int j = 0; j < N; j++) {
				int len = 0;
				if (str.charAt(j) >= 65 && str.charAt(j) <= 90) {
					len = str.charAt(j) - 38;
					pq.add(new LANCable(i, j + 1, len));
				} else if (str.charAt(j) >= 97 && str.charAt(j) <= 122) {
					len = str.charAt(j) - 96;
					pq.add(new LANCable(i, j + 1, len));
				}

				sum += len;
			}
		}

		int min = 0;

		while (!pq.isEmpty()) {
			LANCable cur = pq.poll();
			
			if (cur.to == cur.from) continue;

			int toParent = find(cur.to);
			int fromParent = find(cur.from);

			if (toParent != fromParent) {
				union(toParent, fromParent);
				min += cur.len;
			}
		}
		
		Set<Integer> roots = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			roots.add(find(i));
		}
		
		// 모든 컴퓨터가 연결되어 있지 않을 경우
		// 1. N이 1보다 큰데 랜선 길이 합이 0일 경우
		// 2. 랜선 길이 합은 0이 아니지만 연결이 끊겨있을 경우
		if ((min == 0 && N > 1) || roots.size() > 1) System.out.println(-1);
		else System.out.println(sum - min);
	}

	public static int find(int n) {
		if (parent[n] == n)
			return n;
		return find(parent[n]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parent[a] = b;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 2461번 대표 선수 
 * 반별로 오름차순 정렬 후 우선순위 큐 사용
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] student = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				student[i][j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(student[i]); // 반별로 오름차순 정렬
		}
		
		// [능력치, 반, 인덱스] : 능력치 기준 오름차순 정렬
		PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
		int maxAbility = Integer.MIN_VALUE; // 능력치 최댓값
		
		// 반 별 가장 앞에 있는 학생 저장
		for (int i = 0; i < N; i++) {
			pq.add(new int[] {student[i][0], i, 0});
			maxAbility = Math.max(maxAbility, student[i][0]);
		}
		
		int minDiff = Integer.MAX_VALUE; // 최소 차이
		
		/*
		 * 12 16 43 67
		 * 7 17 48 68
		 * 14 15 54 77
		 */
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int ability = cur[0];
			int classIdx = cur[1];
			int idx = cur[2];
			
			minDiff = Math.min(minDiff, maxAbility - ability); // 최소 차이 갱신
			
			if (idx + 1 == M) break;
			
			int nextAbility = student[classIdx][idx + 1];
			pq.add(new int[] {nextAbility, classIdx, idx + 1});
			
			maxAbility = Math.max(maxAbility, nextAbility);
		}
		
		System.out.println(minDiff);
	}
}

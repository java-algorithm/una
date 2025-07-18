import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<int[]>[] graph; // 부품 저장 리스트
    static int[] indegree; // 진입 차수 저장할 배열
    static int[][] dp; // dp[X][Y] : X를 만들기 위해 필요한 기본 부품 Y의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 완제품 번호
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;

        graph = new ArrayList[N + 1];
        indegree = new int[N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()); // 완성품
            int Y = Integer.parseInt(st.nextToken()); // 부품
            int K = Integer.parseInt(st.nextToken()); // 개수

            graph[Y].add(new int[]{X, K}); // Y K개로 X를 만들기
            indegree[X]++; // 진입 차수 계산
        }

        bfs();

        for (int i = 1; i < N; i++) {
            if (dp[N][i] > 0) {
                System.out.println(i + " " + dp[N][i]);
            }
        }
    }

    public static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) { // 기본 부품일 경우
                q.add(i); // 큐에 넣기
                dp[i][i] = 1; // 기본 부품은 본인 1개만 필요
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] next : graph[cur]) {
                int target = next[0]; // 만들 수 있는 부품 또는 완제품
                int need = next[1]; // 필요한 개수

                for (int i = 1; i <= N; i++) {
                    dp[target][i] += dp[cur][i] * need;
                }

                indegree[target]--;

                // 차수가 0이 되면 큐에 넣기 : 차수가 적은 것부터 탐색할 수 있도록
                if (indegree[target] == 0) q.add(target);
            }
        }
    }
}

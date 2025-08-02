import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_TIME = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 참가자 수
        int T = Integer.parseInt(st.nextToken()); // 스터디 시간

        int[] diff = new int[MAX_TIME]; // 차이 배열
        int maxTime = 0; // 참가자들 시간 중 최대 시간

        for (int i = 0; i < N; i++) {
            int K = Integer.parseInt(br.readLine());
            
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());

                diff[S] += 1; // 이때부터 1명 들어옴
                diff[E] -= 1; // 이때부터 1명 나감
                maxTime = Math.max(maxTime, E);
            }
        }

        // 누적합으로 시간별 참여자 수 구하기
        int[] possible = new int[MAX_TIME];
        possible[0] = diff[0];

        for (int i = 1; i <= maxTime; i++) {
            possible[i] = possible[i - 1] + diff[i];
        }

        // 초기 T시간 구간의 합 구하기
        long sum = 0;
        for (int i = 0; i < T; i++) {
            sum += possible[i];
        }

        long maxSum = sum;
        int startTime = 0;

        // 슬라이딩 윈도우로 최대 만족도 구간 찾기
        for (int i = T; i <= maxTime; i++) {
            sum = sum - possible[i - T] + possible[i];

            if (sum > maxSum) {
                maxSum = sum;
                startTime = i - T + 1;
            }
        }

        System.out.println(startTime + " " + (startTime + T));
    }
}

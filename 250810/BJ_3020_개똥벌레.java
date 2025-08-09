import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] down = new int[h + 1];
        int[] up = new int[h + 1];

        for (int i = 0; i < n; i++) {
            int size = Integer.parseInt(br.readLine());

            if (i % 2 == 1) down[size]++; // 석순 저장
            else up[size]++; // 종유석 저장
        }

        // 석순 누적합 : 길이가 i 이상인 석순의 수
        for (int i = h - 1; i >= 1; i--) {
            down[i] += down[i + 1];
        }

        // 종유석 누적합: 길이가 i 이상인 종유석의 수
        for (int i = h - 1; i >= 1; i--) {
            up[i] += up[i + 1];
        }

        int min = Integer.MAX_VALUE; // 최솟값
        int cnt = 0;

        for (int height = 1; height <= h; height++) {
            int crash = down[height] + up[h - height + 1];

            if (crash < min) {
                min = crash;
                cnt = 1;
            } else if (crash == min) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int maxLen = -1;
        int first = 0, second = 1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {                
                int len = Math.min(words[i].length(), words[j].length());
                int sameWordLen = Math.min(words[i].length(), words[j].length());
                
                for (int k = 0; k < len; k++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) {
                        sameWordLen = k;
                        break;
                    }
                }
                
                if (sameWordLen > maxLen) {
                    maxLen = sameWordLen;
                    first = i;
                    second = j;
                } else if (sameWordLen == maxLen) {
                    if (i < first || (i == first && j < second)) {
                        first = i;
                        second = j;
                    }
                }
            }
        }

        System.out.println(words[first]);
        System.out.println(words[second]);
    }
}

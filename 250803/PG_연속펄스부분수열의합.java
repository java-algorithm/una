/**
 * 프로그래머스 연속 펄스 부분 수열의 합
 * - DP
 */

class Solution {
    public long solution(int[] sequence) {
        // dp[i][0] : i번째에 곱할 값이 양수일 때
        // dp[i][1] : i번째에 곱할 값이 음수일 때
        long[][] dp = new long[sequence.length][2];
        
        dp[0][0] = sequence[0];
        dp[0][1] = -sequence[0];
        
        long max = Math.max(dp[0][0], dp[0][1]);
        
        for (int i = 1; i < sequence.length; i++) {
            // 이전에 곱하는 값이 음수여야 함
            dp[i][0] = Math.max(sequence[i], dp[i - 1][1] + sequence[i]);
            
            // 이전에 곱하는 값이 양수여야 함
            dp[i][1] = Math.max(-sequence[i], dp[i - 1][0] - sequence[i]);
            
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        
        return max;
    }
}

class Solution {
    private static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        // dp[i][0]: i번째 열이 마름모로 끝나는 경우의 수
        // dp[i][1]: i번째 열이 세모로 끝나는 경우의 수
        long[][] dp = new long[n + 1][2];

        // 기본 값 세팅
        // 첫번째 열이 마름모로 끝날 경우 tops가 있어도 경우의 수는 1로 같음
        dp[1][0] = 1;
        // 세모로 끝날 경우 tops가 없으면 2가지, tops가 있으면 3가지
        dp[1][1] = 2 + tops[0];

        for (int i = 2; i <= n; i++) {
            int top = tops[i - 1];

            // i번째가 마름모로 끝나는 경우
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;

            // i-1번째가 마름모였을 경우 세모 + tops가 있으면 4번 마름모
            long fromRhombus = dp[i - 1][0] * (1 + top);
            // i-1번째가 마름모가 아닐 경우 2번 마름모, 세모 + tops가 있으면 4번 마름모
            long fromNotRhombus = dp[i - 1][1] * (2 + top);
            dp[i][1] = (fromRhombus + fromNotRhombus) % MOD;
        }

        return (int)((dp[n][0] + dp[n][1]) % MOD);
    }
}

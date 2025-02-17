/**
 * 프로그래머스 징검다리 건너기
 * - 매개변수 탐색
 * - 0이 k-1개 연속으로 나와야 건널 수 있음
 */

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        
        for (int stone : stones) {
            left = Math.min(left, stone);
            right = Math.max(right, stone);
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            int zeroCount = 0;
            
            for (int stone : stones) {
                if (stone - mid <= 0) zeroCount++;
                else zeroCount = 0;
                
                if (zeroCount >= k) break;
            }
            
            if (zeroCount < k) {
                answer = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
            
        return left;
    }
}

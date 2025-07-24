/**
 * 프로그래머스 숫자 게임
 * - 정렬 + 투 포인터
 */

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {        
        int answer = 0;
            
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = 0; // A 인덱스
        int bIdx = 0; // B 인덱스
        
        while (aIdx < A.length && bIdx < A.length) {
            if (A[aIdx] < B[bIdx]) {
                aIdx++;
                answer++;
            }
            bIdx++;
        }
        
        return answer;
    }
}
